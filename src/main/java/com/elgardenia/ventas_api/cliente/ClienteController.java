package com.elgardenia.ventas_api.cliente;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elgardenia.ventas_api.cliente.dto.ClienteRequest;
import com.elgardenia.ventas_api.cliente.dto.ClienteResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public List<ClienteResponse> obtenerClientes() {
        return service.obtenerClientes();
    }

    @PostMapping
    public ClienteResponse crearCliente(@Valid @RequestBody ClienteRequest request) {
        return service.crearCliente(request);
    }

    @PutMapping("/{id}")
    public ClienteResponse actualizarCliente(
        @PathVariable Integer id,
        @Valid @RequestBody ClienteRequest request
    ) {
        return service.actualizarCliente(id, request);
    }
}
