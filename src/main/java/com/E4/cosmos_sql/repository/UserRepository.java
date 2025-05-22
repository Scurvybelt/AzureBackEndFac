package com.E4.cosmos_sql.repository;

import com.E4.cosmos_sql.model.Usuario;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCosmosRepository<Usuario, String> {
    // Puedes agregar métodos personalizados si es necesario, por ejemplo:
    // Flux<Usuario> findByNombre(String nombre);


}