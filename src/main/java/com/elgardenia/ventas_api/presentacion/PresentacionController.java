package com.elgardenia.ventas_api.presentacion;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController 
@RequiredArgsConstructor 
public class PresentacionController {
    private final PresentacionService service;

    @GetMapping("/presentacion")
    public List<Presentacion> getPresentaciones() {
        return service.getPresentaciones();
    }

    @PostMapping("/presentacion")
    @ResponseStatus(HttpStatus.CREATED)
    public Presentacion postPresentacion(@RequestBody Presentacion presentacion) {
        return service.postPresentacion(presentacion);
    }
}
