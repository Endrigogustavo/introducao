package io.github.fatec.repository;

import io.github.fatec.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Cliente salve(Cliente cliente);
    List<Cliente> buscarTodos();
    Optional<Cliente> buscarPorId(String id);
    Cliente atualizar(Cliente cliente);
    void deletar(String id);
}