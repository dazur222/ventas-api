package com.elgardenia.ventas_api.tipoenvase;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/tipos-envases")
@RequiredArgsConstructor 
public class TipoEnvaseController {
    private final TipoEnvaseService service;

    @GetMapping
    public List<TipoEnvase> getTiposEnvase(){
        return service.getTiposEnvases();
    }
    
}
