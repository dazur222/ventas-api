package com.elgardenia.ventas_api.municipio.dto;

public record MunicipioResponse(
    Integer id,
    String nombre,
    Integer estadoId,
    String estadoNombre
) {
}
