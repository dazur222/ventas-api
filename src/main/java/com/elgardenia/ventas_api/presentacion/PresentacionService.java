package com.elgardenia.ventas_api.presentacion;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PresentacionService {
    private final PresentacionRepository repository;
    
    public List<Presentacion> getPresentaciones(){
        return  repository.findAll();
    }

}
