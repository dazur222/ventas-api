package com.elgardenia.ventas_api.cerveza;

import org.springframework.stereotype.Component;

import com.elgardenia.ventas_api.cerveza.dto.CervezaRequest;
import com.elgardenia.ventas_api.cerveza.dto.CervezaResponse;

@Component 
public class CervezaMapper {
   

    public CervezaResponse modelToDto(Cerveza cerveza){
        return new CervezaResponse(cerveza.getId(),cerveza.getNombre(),cerveza.getGradosAlcohol(),cerveza.getIbu(),cerveza.getEstiloCerveza().getNombre());
    }    

    public static Cerveza dtoToModel(CervezaRequest request){
        Cerveza cerveza = new  Cerveza();

        cerveza.setNombre(request.nombre());
        cerveza.setGradosAlcohol(request.gradosAlcohol());
        cerveza.setIbu(request.ibu());
        // dentro de el mapper no se hacen llamadas a la db no buscamos el 
        // estilo que corresponde con el id y se lo asignamos

        return cerveza;
    }


}
