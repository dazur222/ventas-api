package com.elgardenia.ventas_api.venta.dto;

import java.math.BigDecimal;

public record PartidaResponse(
    Integer id,
    Integer productoId,
    String productoNombre,
    BigDecimal cantidad,
    BigDecimal precioUnitario,
    BigDecimal importeBase,
    BigDecimal iepsTasa,
    BigDecimal iepsMonto,
    BigDecimal ivaTasa,
    BigDecimal ivaMonto,
    BigDecimal totalPartida
) {
}
