package io.github.fatec.repository;

import io.github.fatec.entity.Cliente;
import io.github.fatec.repository.adapter.ClienteRepositoryAdapter;
import io.github.fatec.repository.mongo.ClienteRepositoryWithMongoDB;
import io.github.fatec.repository.orm.ClienteOrmMongo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteRepositoryWithMongoDB repository;

    public ClienteRepositoryImpl(ClienteRepositoryWithMongoDB repository) {
        this.repository = repository;
    }

    @Override
    public Cliente salve(Cliente cliente) {
        ClienteOrmMongo orm = ClienteRepositoryAdapter.castEntity(cliente);
        ClienteOrmMongo ormSave = repository.save(orm);
        return ClienteRepositoryAdapter.castOrm(ormSave);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return repository.findAll()
                .stream()
                .map(ClienteRepositoryAdapter::castOrm)
                .toList();
    }

    @Override
    public Optional<Cliente> buscarPorId(String id) {
        return repository.findById(id)
                .map(ClienteRepositoryAdapter::castOrm);
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        ClienteOrmMongo orm = ClienteRepositoryAdapter.castEntity(cliente);
        ClienteOrmMongo ormSalvo = repository.save(orm);
        return ClienteRepositoryAdapter.castOrm(ormSalvo);
    }

    @Override
    public void deletar(String id) {
        repository.deleteById(id);
    }
}
