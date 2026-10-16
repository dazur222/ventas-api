package com.elgardenia.ventas_api.producto;

import java.math.BigDecimal;

import org.hibernate.annotations.SecondaryRow;

import com.elgardenia.ventas_api.cerveza.Cerveza;
import com.elgardenia.ventas_api.presentacion.Presentacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table
@Setter 
@Getter 
public class Producto {
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "cerveza_id")
private Cerveza cerveza;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "presentacion_id")
private Presentacion presentacion;


@Column(name =  "precio_base") 
private BigDecimal precioBase;

@Column 
private Boolean activo;

}
