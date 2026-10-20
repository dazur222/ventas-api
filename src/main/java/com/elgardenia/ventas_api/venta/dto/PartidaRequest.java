package com.elgardenia.ventas_api.venta.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PartidaRequest(
    @NotNull @Positive Integer productoId,
    @NotNull @DecimalMin(value = "1") @Digits(integer = 10, fraction = 0) BigDecimal cantidad,
    @NotNull @DecimalMin(value = "0") @Digits(integer = 8, fraction = 4) BigDecimal precioUnitario
) {
}
