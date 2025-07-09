package com.carlosribeiro.apirestfulv1.controller;

import com.carlosribeiro.apirestfulv1.model.Usuario;
import com.carlosribeiro.apirestfulv1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/cadastro")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getConta() == null || usuario.getSenha() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(java.util.Collections.singletonMap("message", "Conta e senha são obrigatórias"));
        }
        Optional<Usuario> existente = usuarioRepository.findByConta(usuario.getConta());
        if (existente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(java.util.Collections.singletonMap("message", "Conta já cadastrada"));
        }
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
