package com.elgardenia.ventas_api.cerveza;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elgardenia.ventas_api.cerveza.dto.CervezaResponse;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CervezaService {

    private final CervezaRepository repository;
    private final CervezaMapper mapper;

    public List<CervezaResponse> getCerveza(){
        return repository.findAll().stream().map(mapper::modelToDto).toList();
    }

    
}
