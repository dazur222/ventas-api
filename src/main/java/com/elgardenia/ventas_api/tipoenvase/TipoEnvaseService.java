package com.elgardenia.ventas_api.tipoenvase;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class TipoEnvaseService {
   private final TipoEnvaseRepository repository;

   public List<TipoEnvase> getTiposEnvases(){
    return repository.findAll();
   } 



}
