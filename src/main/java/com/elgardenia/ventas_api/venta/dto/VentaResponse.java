package com.elgardenia.ventas_api.venta.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record VentaResponse(
    Integer id,
    String folio,
    LocalDateTime fechaVenta,
    Integer clienteId,
    String clienteNombre,
    Integer vendedorId,
    BigDecimal subtotal,
    BigDecimal iepsTotal,
    BigDecimal ivaTotal,
    BigDecimal total,
    LocalDate fechaVencimiento,
    String estadoCobranza,
    BigDecimal totalPagado,
    BigDecimal saldoPendiente,
    String observaciones,
    LocalDateTime fechaCreacion,
    List<PartidaResponse> partidas
) {
}
