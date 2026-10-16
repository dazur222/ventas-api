package com.elgardenia.ventas_api.cerveza;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CervezaService {

    private final CervezaRepository repository;

    public List<Cerveza> getCerveza(){
        return repository.findAll();
    }

    
}
