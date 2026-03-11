package io.github.fatec.controller.adapter;

import io.github.fatec.controller.dto.request.ClientRequest;
import io.github.fatec.controller.dto.response.ClienteResponse;
import io.github.fatec.entity.Cliente;

import java.util.UUID;

public class ClienteControllerAdapter {
    private ClienteControllerAdapter() {
    }

    public static Cliente castRequest(ClientRequest request) {
        return new Cliente(
                UUID.randomUUID().toString(),
                request.nome(),
                request.endereco(),
                request.telefone());
    }

    public static Cliente castRequestWithId(String id, ClientRequest request) {
        return new Cliente(
                id,
                request.nome(),
                request.endereco(),
                request.telefone());
    }

    public static ClienteResponse castResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.id(),
                cliente.nome(),
                cliente.endereco(),
                cliente.telefone());
    }
}
