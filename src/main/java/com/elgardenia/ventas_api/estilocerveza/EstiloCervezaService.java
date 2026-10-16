package com.elgardenia.ventas_api.estilocerveza;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class EstiloCervezaService {
    private final EstiloCervezaRepository repository;

    public List<EstiloCerveza> getEstilosCervezas(){
        return repository.findAll();
    } 
    public EstiloCerveza getEstiloCerveza(Integer id){
        return repository.findById(id).orElse(null);
    }

    
}
