package com.elgardenia.ventas_api.cerveza.dto;

import java.math.BigDecimal;

public record CervezaResponse(
    Integer id,
    String nombre,
    BigDecimal ibu,
    String estiloCerveza
) {}