package com.elgardenia.ventas_api.venta;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.elgardenia.ventas_api.venta.dto.VentaRequest;
import com.elgardenia.ventas_api.venta.dto.VentaResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/venta")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService service;

    @GetMapping
    public List<VentaResponse> obtenerVentas() {
        return service.obtenerVentas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponse crearVenta(@Valid @RequestBody VentaRequest request) {
        return service.crearVenta(request);
    }
}
