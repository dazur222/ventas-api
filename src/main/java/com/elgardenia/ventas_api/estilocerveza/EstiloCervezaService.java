package com.elgardenia.ventas_api.estilocerveza;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class EstiloCervezaService {

    private final EstiloCervezaRepository repository;

    public List<EstiloCerveza> getEstilosCerveza(){
        return  repository.findAll();
    }

    
}
