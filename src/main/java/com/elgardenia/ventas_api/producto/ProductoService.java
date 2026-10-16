package com.elgardenia.ventas_api.producto;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ProductoService {

    private final ProductoRepository repository;

    public List<Producto> getProductos(){
        return repository.findAll();
    }
    
}
