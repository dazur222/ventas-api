package com.elgardenia.ventas_api.presentacion;

import java.math.BigDecimal;

import com.elgardenia.ventas_api.tipoenvase.TipoEnvase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
public class Presentacion {
        
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column 
    private String nombre;
    @Column 
    private  BigDecimal litros;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="tipo_envase_id")
    private TipoEnvase tipoEnvase;

    

    
}
