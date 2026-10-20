package com.elgardenia.ventas_api.formapago;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elgardenia.ventas_api.formapago.dto.FormaPagoResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/forma-pago")
@RequiredArgsConstructor
public class FormaPagoController {

    private final FormaPagoService service;

    @GetMapping
    public List<FormaPagoResponse> obtenerFormasPago() {
        return service.obtenerFormasPago();
    }
}
