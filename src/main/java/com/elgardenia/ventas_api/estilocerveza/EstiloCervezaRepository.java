package com.elgardenia.ventas_api.estilocerveza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface  EstiloCervezaRepository extends JpaRepository<EstiloCerveza,Integer>{
    
}
