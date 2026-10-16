package com.elgardenia.ventas_api.cerveza;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
public class CervezaController {
    
    private final CervezaService service;

    @GetMapping("/cerveza")
    public List<Cerveza> getCerveza(){
        // System.out.println("papu");
        return service.getCerveza();
    }


}
