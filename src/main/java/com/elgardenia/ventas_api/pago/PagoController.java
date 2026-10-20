package com.elgardenia.ventas_api.pago;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.elgardenia.ventas_api.pago.dto.PagoRequest;
import com.elgardenia.ventas_api.pago.dto.PagoResponse;
import com.elgardenia.ventas_api.venta.dto.VentaResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/venta/{ventaId}/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService service;

    @GetMapping
    public List<PagoResponse> obtenerPagos(@PathVariable Integer ventaId) {
        return service.obtenerPagos(ventaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponse registrarPago(
        @PathVariable Integer ventaId,
        @Valid @RequestBody PagoRequest request
    ) {
        return service.registrarPago(ventaId, request);
    }
}
