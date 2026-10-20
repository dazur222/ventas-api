package com.elgardenia.ventas_api.tipocliente;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tipo-cliente")
@RequiredArgsConstructor
public class TipoClienteController {

    private final TipoClienteService service;

    @GetMapping
    public List<TipoCliente> getTiposCliente() {
        return service.getTiposCliente();
    }
}
