package com.elgardenia.ventas_api.venta;

import java.math.BigDecimal;

import com.elgardenia.ventas_api.producto.Producto;

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
import lombok.Setter;

@Entity
@Table(name = "partida")
@Getter
@Setter
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 12, scale = 4)
    private BigDecimal precioUnitario;

    @Column(name = "importe_base", nullable = false, precision = 12, scale = 2)
    private BigDecimal importeBase;

    @Column(name = "ieps_tasa", nullable = false, precision = 5, scale = 4)
    private BigDecimal iepsTasa;

    @Column(name = "ieps_monto", nullable = false, precision = 12, scale = 2)
    private BigDecimal iepsMonto;

    @Column(name = "iva_tasa", nullable = false, precision = 5, scale = 4)
    private BigDecimal ivaTasa;

    @Column(name = "iva_monto", nullable = false, precision = 12, scale = 2)
    private BigDecimal ivaMonto;

    @Column(name = "total_partida", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPartida;
}
