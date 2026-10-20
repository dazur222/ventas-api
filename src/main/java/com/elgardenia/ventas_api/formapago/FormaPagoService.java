package com.elgardenia.ventas_api.formapago;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elgardenia.ventas_api.formapago.dto.FormaPagoResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FormaPagoService {

    private final FormaPagoRepository repository;
    private final FormaPagoMapper mapper;

    @Transactional(readOnly = true)
    public List<FormaPagoResponse> obtenerFormasPago() {
        return repository.findAllByActivoTrueOrderByNombreAsc()
            .stream()
            .map(mapper::toResponse)
            .toList();
    }
}
