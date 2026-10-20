package com.elgardenia.ventas_api.cliente;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elgardenia.ventas_api.cliente.dto.ClienteRequest;
import com.elgardenia.ventas_api.cliente.dto.ClienteResponse;
import com.elgardenia.ventas_api.razonsocial.RazonSocialRepository;
import com.elgardenia.ventas_api.tipocliente.TipoClienteRepository;
import com.elgardenia.ventas_api.municipio.MunicipioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;
    private final TipoClienteRepository tipoClienteRepository;
    private final RazonSocialRepository razonSocialRepository;
    private final MunicipioRepository municipioRepository;

    public List<ClienteResponse> obtenerClientes() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public ClienteResponse crearCliente(ClienteRequest request) {
        Cliente cliente = mapper.toEntity(request);
        asignarRelaciones(request, cliente);
        return mapper.toResponse(repository.save(cliente));
    }

    public ClienteResponse actualizarCliente(Integer id, ClienteRequest request) {
        Cliente cliente = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        mapper.copyRequestToEntity(request, cliente);
        asignarRelaciones(request, cliente);
        return mapper.toResponse(repository.save(cliente));
    }

    private void asignarRelaciones(ClienteRequest request, Cliente cliente) {
        cliente.setMunicipio(request.municipioId() == null ? null : municipioRepository.findById(request.municipioId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipio no encontrado")));
        cliente.setTipoCliente(request.tipoClienteId() == null ? null : tipoClienteRepository.findById(request.tipoClienteId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de cliente no encontrado")));
        cliente.setRazonSocial(request.razonSocialId() == null ? null : razonSocialRepository.findById(request.razonSocialId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Razón social no encontrada")));
    }
}
