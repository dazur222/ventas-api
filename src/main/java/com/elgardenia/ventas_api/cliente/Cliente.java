package com.elgardenia.ventas_api.cliente;

import java.time.LocalDateTime;

import com.elgardenia.ventas_api.razonsocial.RazonSocial;
import com.elgardenia.ventas_api.tipocliente.TipoCliente;
import com.elgardenia.ventas_api.municipio.Municipio;
import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_negocio", nullable = false)
    private String nombreNegocio;

    @Column(name = "nombre_contacto")
    private String nombreContacto;

    @Column
    private String telefono;

    @Column
    private String correo;

    @Column
    private String direccion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_cliente_id")
    private TipoCliente tipoCliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "razon_social_id")
    private RazonSocial razonSocial;

    @Column(nullable = false)
    private Boolean activo;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column
    private String nota;
}
