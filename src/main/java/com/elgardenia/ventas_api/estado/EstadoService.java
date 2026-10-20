package com.elgardenia.ventas_api.estado;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository repository;

    public List<Estado> getEstados() {
        return repository.findAll();
    }
}
