package com.elgardenia.ventas_api.cerveza;

import org.springframework.stereotype.Component;

import com.elgardenia.ventas_api.cerveza.dto.CervezaResponse;

@Component 
public class CervezaMapper {
   
    public CervezaResponse modelToDto(Cerveza cerveza){
        return new CervezaResponse(cerveza.getId(),cerveza.getNombre(),cerveza.getIbu(),cerveza.getEstiloCerveza().getNombre());

    }    


}
