package com.elgardenia.ventas_api.presentacion;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequiredArgsConstructor 
public class PresentacionController {
    private final PresentacionService service;

    @GetMapping("/presentaciones")
    public List<Presentacion> getPresentaciones() {
        return service.getPresentaciones();
    }
    
    

}
