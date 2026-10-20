package com.elgardenia.ventas_api.tipocliente;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoClienteService {

    private final TipoClienteRepository repository;

    public List<TipoCliente> getTiposCliente() {
        return repository.findAll();
    }
}
