package com.elgardenia.ventas_api.pago;

import java.math.BigDecimal;

public interface PagoTotalVenta {

    Integer getVentaId();

    BigDecimal getTotalPagado();
}
