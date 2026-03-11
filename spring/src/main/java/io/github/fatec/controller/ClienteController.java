package io.github.fatec.controller;

import io.github.fatec.controller.adapter.ClienteControllerAdapter;
import io.github.fatec.controller.dto.request.ClientRequest;
import io.github.fatec.controller.dto.response.ClienteResponse;
import io.github.fatec.entity.Cliente;
import io.github.fatec.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    public final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/cadastrar")
    public ClienteResponse salvar(@RequestBody ClientRequest request) {
        Cliente cliente = ClienteControllerAdapter.castRequest(request);
        Cliente clienteSalvo = clienteRepository.salve(cliente);
        return ClienteControllerAdapter.castResponse(clienteSalvo);
    }

    @GetMapping
    public List<ClienteResponse> buscarTodos() {
        return clienteRepository.buscarTodos()
                .stream()
                .map(ClienteControllerAdapter::castResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable String id) {
        return clienteRepository.buscarPorId(id)
                .map(ClienteControllerAdapter::castResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable String id, @RequestBody ClientRequest request) {
        return clienteRepository.buscarPorId(id)
                .map(clienteExistente -> {
                    Cliente clienteAtualizado = ClienteControllerAdapter.castRequestWithId(id, request);
                    Cliente salvo = clienteRepository.atualizar(clienteAtualizado);
                    return ResponseEntity.ok(ClienteControllerAdapter.castResponse(salvo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        return clienteRepository.buscarPorId(id)
                .map(cliente -> {
                    clienteRepository.deletar(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
