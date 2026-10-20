package com.elgardenia.ventas_api.cliente.dto;

import java.time.LocalDateTime;

public record ClienteResponse(
    Integer id,
    String nombreNegocio,
    String nombreContacto,
    String telefono,
    String correo,
    String direccion,
    Integer municipioId,
    Integer tipoClienteId,
    Integer razonSocialId,
    Boolean activo,
    LocalDateTime fechaCreacion,
    String nota
) {
}
