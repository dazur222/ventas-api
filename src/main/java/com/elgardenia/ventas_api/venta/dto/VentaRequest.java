package com.elgardenia.ventas_api.venta.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record VentaRequest(
    @NotNull @Positive Integer clienteId,
    LocalDateTime fechaVenta,
    LocalDate fechaVencimiento,
    @Size(max = 500) String observaciones,
    @NotEmpty List<@Valid PartidaRequest> partidas
) {
}
