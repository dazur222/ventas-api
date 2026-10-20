package com.elgardenia.ventas_api.formapago;

import org.springframework.stereotype.Component;

import com.elgardenia.ventas_api.formapago.dto.FormaPagoResponse;

@Component
public class FormaPagoMapper {

    public FormaPagoResponse toResponse(FormaPago formaPago) {
        return new FormaPagoResponse(formaPago.getId(), formaPago.getNombre());
    }
}
