package com.elgardenia.ventas_api.razonsocial;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RazonSocialService {

    private final RazonSocialRepository repository;

    public List<RazonSocial> getRazonesSociales() {
        return repository.findAll();
    }
}
