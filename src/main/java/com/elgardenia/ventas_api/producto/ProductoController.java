package com.elgardenia.ventas_api.producto;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequiredArgsConstructor 
public class ProductoController {

    private final ProductoService service;

    @GetMapping("/producto")
    public List<Producto> getProductos() {
        return service.getProductos();
    }
    
    
}
