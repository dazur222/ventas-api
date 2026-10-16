package com.elgardenia.ventas_api.cerveza;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.elgardenia.ventas_api.estilocerveza.EstiloCerveza;
import com.elgardenia.ventas_api.tipoenvase.TipoEnvase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@Table(name = "cerveza")
public class Cerveza {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column
    private String nombre;

    @Column(name = "grados_alcohol")
    private BigDecimal gradosAlcohol;


    @Column(precision = 5 , scale = 2)
    private BigDecimal ibu;
    
    @Column 
    private Boolean activo;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="estilo_cerveza_id")
    private EstiloCerveza estiloCerveza;
}
