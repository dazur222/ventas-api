package com.elgardenia.ventas_api.cerveza.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public record CervezaRequest(
    String nombre,
    @NotNull @DecimalMin("0.1") @DecimalMax("100") @Digits(integer = 3, fraction = 2) BigDecimal gradosAlcohol,
    BigDecimal ibu,
    Integer estiloCervezaId
) {}
