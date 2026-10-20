package com.elgardenia.ventas_api.municipio;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elgardenia.ventas_api.municipio.dto.MunicipioResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/municipio")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService service;

    @GetMapping
    public List<MunicipioResponse> getMunicipios() {
        return service.getMunicipios();
    }
}
