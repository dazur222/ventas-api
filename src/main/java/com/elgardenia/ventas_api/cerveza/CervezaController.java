package com.elgardenia.ventas_api.cerveza;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elgardenia.ventas_api.cerveza.dto.CervezaResponse;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
public class CervezaController {
    
    private final CervezaService service;

    @GetMapping("/cerveza")
    public List<CervezaResponse> getCerveza(){
        // System.out.println("papu");
        return service.getCerveza();
    }


}
