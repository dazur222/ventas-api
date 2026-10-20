package com.elgardenia.ventas_api.cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
    @NotBlank String nombreNegocio,
    String nombreContacto,
    String telefono,
    @Email String correo,
    String direccion,
    Integer municipioId,
    Integer tipoClienteId,
    Integer razonSocialId,
    Boolean activo,
    String nota
) {
}
