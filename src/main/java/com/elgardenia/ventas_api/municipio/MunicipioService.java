package com.elgardenia.ventas_api.municipio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elgardenia.ventas_api.municipio.dto.MunicipioResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MunicipioService {

    private final MunicipioRepository repository;

    public List<MunicipioResponse> getMunicipios() {
        return repository.findAll().stream()
            .map(municipio -> new MunicipioResponse(
                municipio.getId(),
                municipio.getNombre(),
                municipio.getEstado().getId(),
                municipio.getEstado().getNombre()
            ))
            .toList();
    }
}
