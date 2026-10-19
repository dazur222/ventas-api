package com.elgardenia.ventas_api.producto;

import org.springframework.web.bind.annotation.RestController;

import com.elgardenia.ventas_api.producto.dto.ProductoRequest;
import com.elgardenia.ventas_api.producto.dto.ProductoResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController 
@RequiredArgsConstructor 
public class ProductoController {

    private final ProductoService service;

    @GetMapping("/producto")
    public List<ProductoResponse> getProductos() {
        return service.getProductos();
    }

    @PostMapping("/producto")
    
    public ProductoResponse postProducto(@RequestBody ProductoRequest request) {
        return service.postProducto(request);
    }

    @PutMapping("/producto/{id}")
    public ProductoResponse uptadeProducto(@PathVariable Integer id, @RequestBody ProductoRequest request) {
        return service.updateProducto(request, id); 
    }
    
    
    
}
