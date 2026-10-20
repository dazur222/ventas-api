package com.elgardenia.ventas_api.pago;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.elgardenia.ventas_api.formapago.FormaPago;
import com.elgardenia.ventas_api.formapago.FormaPagoRepository;
import com.elgardenia.ventas_api.pago.dto.PagoRequest;
import com.elgardenia.ventas_api.pago.dto.PagoResponse;
import com.elgardenia.ventas_api.venta.Venta;
import com.elgardenia.ventas_api.venta.VentaMapper;
import com.elgardenia.ventas_api.venta.VentaRepository;
import com.elgardenia.ventas_api.venta.dto.VentaResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoService {

    private static final String ESTADO_CONFIRMADO = "CONFIRMADO";
    private static final BigDecimal CERO = BigDecimal.ZERO;

    private final PagoRepository pagoRepository;
    private final VentaRepository ventaRepository;
    private final FormaPagoRepository formaPagoRepository;
    private final PagoMapper mapper;
    private final VentaMapper ventaMapper;

    @Transactional(readOnly = true)
    public List<PagoResponse> obtenerPagos(Integer ventaId) {
        if (!ventaRepository.existsById(ventaId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota de venta no encontrada");
        }
        return pagoRepository.findAllByVenta_IdOrderByFechaPagoDescIdDesc(ventaId)
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

    @Transactional
    public VentaResponse registrarPago(Integer ventaId, PagoRequest request) {
        Venta venta = ventaRepository.findByIdForUpdate(ventaId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota de venta no encontrada"));
        FormaPago formaPago = formaPagoRepository.findByIdAndActivoTrue(request.formaPagoId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "La forma de pago no existe o está inactiva"));

        BigDecimal totalPagado = pagoRepository.sumarPagosConfirmados(ventaId);
        BigDecimal saldoPendiente = venta.getTotal().subtract(totalPagado);
        if (saldoPendiente.compareTo(CERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La nota de venta ya está pagada");
        }
        if (request.monto().compareTo(saldoPendiente) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El pago no puede ser mayor al saldo pendiente");
        }

        Pago pago = new Pago();
        pago.setVenta(venta);
        pago.setFechaPago(LocalDateTime.now());
        pago.setMonto(request.monto());
        pago.setEstadoPago(ESTADO_CONFIRMADO);
        pago.setComentarios(request.comentarios());
        pago.setFormaPago(formaPago);
        pagoRepository.save(pago);

        BigDecimal nuevoTotalPagado = totalPagado.add(request.monto());
        String estadoCobranza = nuevoTotalPagado.compareTo(venta.getTotal()) == 0 ? "PAGADA" : "PARCIAL";
        int ventasActualizadas = ventaRepository.actualizarEstadoCobranza(ventaId, estadoCobranza);
        if (ventasActualizadas != 1) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo actualizar el estado de cobranza de la nota");
        }
        venta.setEstadoCobranza(estadoCobranza);

        return ventaMapper.toResponse(venta, nuevoTotalPagado);
    }
}
