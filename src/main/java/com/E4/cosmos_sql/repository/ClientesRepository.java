package com.E4.cosmos_sql.repository;

import com.E4.cosmos_sql.model.Clientes;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClientesRepository extends ReactiveCosmosRepository<Clientes, String> {
    // Métodos personalizados opcionales
//    Mono<Void> deleteById(String id_Usuario);
//    Flux<Clientes> findById_Usuario(String id_Usuario);
}
