package com.E4.cosmos_sql.controller;

import com.E4.cosmos_sql.model.Usuario;
import com.E4.cosmos_sql.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/usuarios")

public class UsuariosController {
    private final UserRepository userRepository;

    @Autowired
    public UsuariosController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Flux<Usuario> getAllUsuarios() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Usuario> getUsuarioById(@PathVariable String id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public Mono<Usuario> createUsuario(@RequestBody Usuario usuario) {
        return userRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Mono<Usuario> updateUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        return userRepository.findById(id)
                .flatMap(existingUsuario -> {
                    usuario.setId_Usuario(existingUsuario.getId_Usuario());
                    return userRepository.save(usuario);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUsuario(@PathVariable String id) {
        return userRepository.deleteById(id);
    }
}