package com.E4.cosmos_sql.repository;

import com.E4.cosmos_sql.model.Usuario;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCosmosRepository<Usuario, String> {
    Mono<Usuario> findByCorreo(String correo);
}