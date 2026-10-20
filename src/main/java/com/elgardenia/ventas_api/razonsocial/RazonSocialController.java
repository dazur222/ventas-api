package com.elgardenia.ventas_api.razonsocial;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/razon-social")
@RequiredArgsConstructor
public class RazonSocialController {

    private final RazonSocialService service;

    @GetMapping
    public List<RazonSocial> getRazonesSociales() {
        return service.getRazonesSociales();
    }
}
