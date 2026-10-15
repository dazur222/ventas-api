package com.elgardenia.ventas_api.estilocerveza;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
public class EstiloCervezaController {
    
    private final EstiloCervezaService service;

    @GetMapping("/estilo-cerveza")
    public List<EstiloCerveza> getEstilosCerveza(){
        return service.getEstilosCerveza();
    }


}
