package com.elgardenia.ventas_api.cliente;

import org.springframework.stereotype.Component;

import com.elgardenia.ventas_api.cliente.dto.ClienteRequest;
import com.elgardenia.ventas_api.cliente.dto.ClienteResponse;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequest request) {
        Cliente cliente = new Cliente();
        copyRequestToEntity(request, cliente);
        if (request.activo() == null) {
            cliente.setActivo(true);
        }
        return cliente;
    }

    public void copyRequestToEntity(ClienteRequest request, Cliente cliente) {
        cliente.setNombreNegocio(request.nombreNegocio());
        cliente.setNombreContacto(request.nombreContacto());
        cliente.setTelefono(request.telefono());
        cliente.setCorreo(request.correo());
        cliente.setDireccion(request.direccion());
        cliente.setNota(request.nota());
        if (request.activo() != null) {
            cliente.setActivo(request.activo());
        }
    }

    public ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
            cliente.getId(),
            cliente.getNombreNegocio(),
            cliente.getNombreContacto(),
            cliente.getTelefono(),
            cliente.getCorreo(),
            cliente.getDireccion(),
            cliente.getMunicipio() == null ? null : cliente.getMunicipio().getId(),
            cliente.getTipoCliente() == null ? null : cliente.getTipoCliente().getId(),
            cliente.getRazonSocial() == null ? null : cliente.getRazonSocial().getId(),
            cliente.getActivo(),
            cliente.getFechaCreacion(),
            cliente.getNota()
        );
    }
}
