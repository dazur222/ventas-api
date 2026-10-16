package com.elgardenia.ventas_api.cerveza.dto;

import java.math.BigDecimal;

public record CervezaRequest(
    String nombre,
    BigDecimal gradosAlcohol,
    BigDecimal ibu,
    Integer estiloCervezaId
) {}
