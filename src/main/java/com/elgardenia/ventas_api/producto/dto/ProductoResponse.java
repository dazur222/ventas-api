package com.elgardenia.ventas_api.producto.dto;

import java.math.BigDecimal;

import com.elgardenia.ventas_api.cerveza.Cerveza;
import com.elgardenia.ventas_api.presentacion.Presentacion;

// @Id 
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Integer id;


// @ManyToOne(fetch = FetchType.EAGER)
// @JoinColumn(name = "cerveza_id")
// private Cerveza cerveza;

// @ManyToOne(fetch = FetchType.EAGER)
// @JoinColumn(name = "presentacion_id")
// private Presentacion presentacion;


// @Column(name =  "precio_base") 
// private BigDecimal precioBase;

// @Column 
// private Boolean activo;

public record ProductoResponse(Integer id,Cerveza cerveza, Presentacion presentacion, BigDecimal precioBase, Boolean activo ) {

}