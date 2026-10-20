package com.elgardenia.ventas_api.pago;

import org.springframework.stereotype.Component;

import com.elgardenia.ventas_api.pago.dto.PagoResponse;

@Component
public class PagoMapper {

    public PagoResponse toResponse(Pago pago) {
        return new PagoResponse(
            pago.getId(),
            pago.getFechaPago(),
            pago.getMonto(),
            pago.getEstadoPago(),
            pago.getComentarios(),
            pago.getFormaPago().getId(),
            pago.getFormaPago().getNombre()
        );
    }
}
