package com.elgardenia.ventas_api.cerveza;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elgardenia.ventas_api.cerveza.dto.CervezaRequest;
import com.elgardenia.ventas_api.cerveza.dto.CervezaResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
public class CervezaController {

    private final CervezaService service;

    @GetMapping("/cerveza")
    public List<CervezaResponse> getCerveza() {
        // System.out.println("papu");
        return service.getCervezas();
    }

    @PostMapping("/cerveza")
    public CervezaResponse postCerveza(@RequestBody CervezaRequest request) {
        return service.postCerveza(request);
    }

    @PutMapping("/cerveza/{id}")
    public CervezaResponse putMethodName(@PathVariable Integer id, @RequestBody CervezaRequest request) {
        return service.updateCerveza(request, id);
    }

}
