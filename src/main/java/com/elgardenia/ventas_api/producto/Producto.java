package com.elgardenia.ventas_api.producto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table
public class Producto {
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
}
