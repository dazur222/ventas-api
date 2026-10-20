package com.elgardenia.ventas_api.pago.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagoResponse(
    Integer id,
    LocalDateTime fechaPago,
    BigDecimal monto,
    String estadoPago,
    String comentarios,
    Long formaPagoId,
    String formaPagoNombre
) {
}
