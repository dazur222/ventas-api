package com.elgardenia.ventas_api.venta;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.elgardenia.ventas_api.producto.Producto;
import com.elgardenia.ventas_api.venta.dto.PartidaResponse;
import com.elgardenia.ventas_api.venta.dto.VentaResponse;

@Component
public class VentaMapper {

    public VentaResponse toResponse(Venta venta, BigDecimal totalPagado) {
        List<PartidaResponse> partidas = venta.getPartidas().stream()
            .map(this::toPartidaResponse)
            .toList();
        return new VentaResponse(
            venta.getId(),
            venta.getFolio(),
            venta.getFechaVenta(),
            venta.getCliente().getId(),
            venta.getCliente().getNombreNegocio(),
            venta.getVendedorId(),
            venta.getSubtotal(),
            venta.getIepsTotal(),
            venta.getIvaTotal(),
            venta.getTotal(),
            venta.getFechaVencimiento(),
            venta.getEstadoCobranza(),
            totalPagado,
            venta.getTotal().subtract(totalPagado),
            venta.getObservaciones(),
            venta.getFechaCreacion(),
            partidas
        );
    }

    private PartidaResponse toPartidaResponse(Partida partida) {
        Producto producto = partida.getProducto();
        String productoNombre = producto.getCerveza().getNombre()
            + " · "
            + producto.getPresentacion().getTipoEnvase().getNombre()
            + " "
            + producto.getPresentacion().getLitros()
            + " L";
        return new PartidaResponse(
            partida.getId(),
            producto.getId(),
            productoNombre,
            partida.getCantidad(),
            partida.getPrecioUnitario(),
            partida.getImporteBase(),
            partida.getIepsTasa(),
            partida.getIepsMonto(),
            partida.getIvaTasa(),
            partida.getIvaMonto(),
            partida.getTotalPartida()
        );
    }
}
