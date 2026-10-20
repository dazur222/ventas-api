package com.elgardenia.ventas_api.venta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.elgardenia.ventas_api.cliente.Cliente;
import com.elgardenia.ventas_api.cliente.ClienteRepository;
import com.elgardenia.ventas_api.producto.Producto;
import com.elgardenia.ventas_api.producto.ProductoRepository;
import com.elgardenia.ventas_api.pago.PagoRepository;
import com.elgardenia.ventas_api.pago.PagoTotalVenta;
import com.elgardenia.ventas_api.venta.dto.PartidaRequest;
import com.elgardenia.ventas_api.venta.dto.VentaRequest;
import com.elgardenia.ventas_api.venta.dto.VentaResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaService {

    private static final BigDecimal IVA_TASA = new BigDecimal("0.1600");
    private static final BigDecimal IEPS_14_O_MENOS = new BigDecimal("0.2650");
    private static final BigDecimal IEPS_MAS_14_HASTA_20 = new BigDecimal("0.3000");
    private static final BigDecimal IEPS_MAS_20 = new BigDecimal("0.5300");
    private static final BigDecimal CERO = BigDecimal.ZERO;
    private static final int ESCALA_MONEDA = 2;
    private static final long FOLIO_LOCK_KEY = 781243105L;

    private final VentaRepository ventaRepository;
    private final VentaMapper mapper;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final PagoRepository pagoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public String obtenerFolioProyectado() {
        return String.format("NV%04d", ventaRepository.findLastFolioNumber() + 1);
    }

    @Transactional(readOnly = true)
    public List<VentaResponse> obtenerVentas() {
        List<Venta> ventas = ventaRepository.findAllByOrderByFechaVentaDescIdDesc();
        if (ventas.isEmpty()) {
            return List.of();
        }

        List<Integer> ventaIds = ventas.stream().map(venta -> venta.getId()).toList();
        Map<Integer, BigDecimal> pagosPorVenta = new HashMap<>();
        for (PagoTotalVenta pagoTotal : pagoRepository.findTotalesConfirmadosPorVentaIds(ventaIds)) {
            pagosPorVenta.put(pagoTotal.getVentaId(), pagoTotal.getTotalPagado());
        }

        return ventas.stream()
            .map(venta -> mapper.toResponse(venta, pagosPorVenta.getOrDefault(venta.getId(), CERO)))
            .toList();
    }

    @Transactional
    public VentaResponse crearVenta(VentaRequest request) {
        entityManager.createNativeQuery("SELECT pg_advisory_xact_lock(:lockKey)")
            .setParameter("lockKey", FOLIO_LOCK_KEY)
            .getSingleResult();

        Cliente cliente = clienteRepository.findById(request.clienteId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        if (!Boolean.TRUE.equals(cliente.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pueden registrar ventas para un cliente inactivo");
        }

        Venta venta = new Venta();
        venta.setFolio(String.format("NV%04d", ventaRepository.findLastFolioNumber() + 1));
        venta.setFechaVenta(request.fechaVenta() == null ? LocalDateTime.now() : request.fechaVenta());
        venta.setCliente(cliente);
        venta.setVendedorId(null);
        venta.setFechaVencimiento(request.fechaVencimiento());
        venta.setEstadoCobranza("PENDIENTE");
        venta.setObservaciones(request.observaciones());

        BigDecimal subtotal = CERO;
        BigDecimal iepsTotal = CERO;
        BigDecimal ivaTotal = CERO;
        BigDecimal total = CERO;

        for (PartidaRequest partidaRequest : request.partidas()) {
            Producto producto = productoRepository.findById(partidaRequest.productoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
            if (!Boolean.TRUE.equals(producto.getActivo())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pueden agregar productos inactivos a la venta");
            }
            if (producto.getCerveza() == null || producto.getCerveza().getGradosAlcohol() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El producto no tiene graduación alcohólica para calcular IEPS");
            }
            BigDecimal gradosAlcohol = producto.getCerveza().getGradosAlcohol();
            if (gradosAlcohol.compareTo(new BigDecimal("0.1")) < 0
                || gradosAlcohol.compareTo(new BigDecimal("100")) > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La graduación alcohólica debe estar entre 0.1 y 100");
            }
            if (producto.getPresentacion() == null || producto.getPresentacion().getTipoEnvase() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El producto no tiene una presentación válida");
            }

            Partida partida = crearPartida(venta, producto, partidaRequest);
            venta.getPartidas().add(partida);
            subtotal = subtotal.add(partida.getImporteBase());
            iepsTotal = iepsTotal.add(partida.getIepsMonto());
            ivaTotal = ivaTotal.add(partida.getIvaMonto());
            total = total.add(partida.getTotalPartida());
        }

        venta.setSubtotal(subtotal);
        venta.setIepsTotal(iepsTotal);
        venta.setIvaTotal(ivaTotal);
        venta.setTotal(total);

        return mapper.toResponse(ventaRepository.save(venta), CERO);
    }

    private Partida crearPartida(Venta venta, Producto producto, PartidaRequest request) {
        BigDecimal tasaIeps = calcularTasaIeps(producto.getCerveza().getGradosAlcohol());

        System.out.println("la tasa ieps es de "+tasaIeps);
        BigDecimal cantidad = request.cantidad().setScale(0, RoundingMode.UNNECESSARY);
        BigDecimal precioUnitario = request.precioUnitario().setScale(4, RoundingMode.UNNECESSARY);
        BigDecimal importeBase = cantidad.multiply(precioUnitario).setScale(ESCALA_MONEDA, RoundingMode.HALF_UP);
        BigDecimal iepsMonto = importeBase.multiply(tasaIeps).setScale(ESCALA_MONEDA, RoundingMode.HALF_UP);
        BigDecimal ivaMonto = importeBase.add(iepsMonto)
            .multiply(IVA_TASA)
            .setScale(ESCALA_MONEDA, RoundingMode.HALF_UP);

        Partida partida = new Partida();
        partida.setVenta(venta);
        partida.setProducto(producto);
        partida.setCantidad(cantidad);
        partida.setPrecioUnitario(precioUnitario);
        partida.setImporteBase(importeBase);
        partida.setIepsTasa(tasaIeps);
        partida.setIepsMonto(iepsMonto);
        partida.setIvaTasa(IVA_TASA);
        partida.setIvaMonto(ivaMonto);
        partida.setTotalPartida(importeBase.add(iepsMonto).add(ivaMonto).setScale(ESCALA_MONEDA));
        return partida;
    }

    static BigDecimal calcularTasaIeps(BigDecimal gradosAlcohol) {
        
        System.out.println("Los grados de alcohol de la cheve son");
        System.out.println(gradosAlcohol);
        if (gradosAlcohol.compareTo(new BigDecimal("14")) <= 0) {
            return IEPS_14_O_MENOS;
        }
        if (gradosAlcohol.compareTo(new BigDecimal("20")) <= 0) {
            return IEPS_MAS_14_HASTA_20;
        }
        return IEPS_MAS_20;
    }

}
