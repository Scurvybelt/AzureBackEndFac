package com.E4.cosmos_sql.controller;

import com.E4.cosmos_sql.model.Clientes;
import com.E4.cosmos_sql.repository.ClientesRepository;
import com.E4.cosmos_sql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    private final ClientesRepository clientesRepository;


    @Autowired
    public ClientesController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;

    }

    @GetMapping
    public Flux<Clientes> getAllClientes() {
        return clientesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Clientes>> getClienteById(@PathVariable String id) {
        return clientesRepository.findById(id)
                .map(cliente -> ResponseEntity.ok(cliente))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> createCliente(@RequestBody Clientes cliente) {
        cliente.setFecha_Registro(java.time.LocalDate.now()); // Set current date
        return clientesRepository.save(cliente).map(savedCliente -> {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Cliente creado exitosamente");
            response.put("cliente", savedCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> updateCliente(@PathVariable String id, @RequestBody Clientes cliente) {
        return clientesRepository.findById(id)
                .flatMap(existingCliente -> {
                    cliente.setId_Cliente(existingCliente.getId_Cliente());
                    cliente.setFecha_Registro(existingCliente.getFecha_Registro()); // Preserve original registration date
                    return clientesRepository.save(cliente);
                })
                .map(updatedCliente -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("status", "success");
                    response.put("message", "Cliente actualizado exitosamente");
                    response.put("cliente", updatedCliente);
                    return ResponseEntity.ok(response);
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }




//    @DeleteMapping("/{id}")
//    public Mono<ResponseEntity<Void>> deleteCliente(@PathVariable String id) {
//        return clientesRepository.findById(id)
//                .flatMap(existingCliente -> clientesRepository.deleteById(id)
//                        .then(Mono.just(ResponseEntity.noContent().build())))
//                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
//    }


}