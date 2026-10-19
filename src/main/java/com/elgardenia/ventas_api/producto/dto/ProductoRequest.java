package com.elgardenia.ventas_api.producto.dto;

import java.math.BigDecimal;

public record ProductoRequest(Integer cervezaId,Integer presentacionId,BigDecimal precioBase,Boolean activo) {
}