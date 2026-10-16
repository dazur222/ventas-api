package com.elgardenia.ventas_api.cerveza;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elgardenia.ventas_api.cerveza.dto.CervezaRequest;
import com.elgardenia.ventas_api.cerveza.dto.CervezaResponse;
import com.elgardenia.ventas_api.estilocerveza.EstiloCerveza;
import com.elgardenia.ventas_api.estilocerveza.EstiloCervezaService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CervezaService {

    private final CervezaRepository repository;
    private final CervezaMapper mapper;
    private final EstiloCervezaService estiloCervezaService;

    public List<CervezaResponse> getCerveza(){
        return repository.findAll().stream().map(mapper::modelToDto).toList();
    }

    public CervezaResponse postCerveza(CervezaRequest request){
        // nombre, ibu, estilo_cerveza_id
        Cerveza cerveza = CervezaMapper.dtoToModel(request);
    
        EstiloCerveza estilo = estiloCervezaService.getEstiloCerveza(request.estiloCervezaId());

        cerveza.setEstiloCerveza(estilo);
        cerveza.setActivo(true);

        Cerveza nuevaCerveza = repository.save(cerveza);

        return mapper.modelToDto(nuevaCerveza);
        

    }
    
}
