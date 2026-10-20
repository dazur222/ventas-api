package com.elgardenia.ventas_api.presentacion;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elgardenia.ventas_api.tipoenvase.TipoEnvase;
import com.elgardenia.ventas_api.tipoenvase.TipoEnvaseRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PresentacionService {
    private final PresentacionRepository repository;
    private final TipoEnvaseRepository tipoEnvaseRepository;
    
    public List<Presentacion> getPresentaciones(){
        return  repository.findAll();
    }

    public Presentacion postPresentacion(Presentacion request) {
        if (request.getLitros() == null || request.getTipoEnvase() == null
            || request.getTipoEnvase().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los litros y el tipo de envase son obligatorios");
        }

        TipoEnvase tipoEnvase = tipoEnvaseRepository.findById(request.getTipoEnvase().getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de envase no encontrado"));

        Presentacion presentacion = new Presentacion();
        presentacion.setLitros(request.getLitros());
        presentacion.setTipoEnvase(tipoEnvase);
        return repository.save(presentacion);
    }

    public Presentacion getPresentacion(Integer id){
        return  repository.findById(id).orElse(null);
    }

}
