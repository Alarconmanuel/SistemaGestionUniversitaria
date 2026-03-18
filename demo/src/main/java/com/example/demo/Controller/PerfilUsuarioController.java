package com.example.demo.Controller;

import com.example.demo.Model.PerfilUsuario;
import com.example.demo.Service.PerfilUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController → maneja peticiones HTTP y retorna JSON
@RestController

// @RequestMapping → todas las rutas empiezan con /api/perfiles
@RequestMapping("/api/perfiles")
public class PerfilUsuarioController {

    private final PerfilUsuarioService perfilUsuarioService;

    public PerfilUsuarioController(PerfilUsuarioService perfilUsuarioService) {
        this.perfilUsuarioService = perfilUsuarioService;
    }

    // HTTP GET /api/perfiles
    // SQL: SELECT * FROM perfiles_usuario
    // Respuesta: 200 OK + lista de perfiles
    @GetMapping
    public ResponseEntity<List<PerfilUsuario>> findAll() {
        return ResponseEntity.ok(perfilUsuarioService.findAll());
    }

    // HTTP GET /api/perfiles/1
    // SQL: SELECT * FROM perfiles_usuario WHERE id = 1
    // Respuesta: 200 OK o 404
    @GetMapping("/{id}")
    public ResponseEntity<PerfilUsuario> findById(@PathVariable Long id) {
        return perfilUsuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // HTTP GET /api/perfiles/documento/123456789
    // SQL: SELECT * FROM perfiles_usuario WHERE documento = '123456789'
    // Respuesta: 200 OK o 404
    @GetMapping("/documento/{documento}")
    public ResponseEntity<PerfilUsuario> findByDocumento(@PathVariable String documento) {
        return perfilUsuarioService.findByDocumento(documento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // HTTP GET /api/perfiles/usuario/1
    // SQL: SELECT * FROM perfiles_usuario WHERE usuario_id = 1
    // Respuesta: 200 OK o 404
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<PerfilUsuario> findByUsuarioId(@PathVariable Long usuarioId) {
        return perfilUsuarioService.findByUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // HTTP POST /api/perfiles + body JSON
    // SQL: INSERT INTO perfiles_usuario (documento, telefono, usuario_id)
    //      VALUES (?, ?, ?)
    // Respuesta: 201 CREATED o 409 CONFLICT si ya existe el documento
    @PostMapping
    public ResponseEntity<PerfilUsuario> save(@RequestBody PerfilUsuario perfil) {
        if (perfilUsuarioService.existsByDocumento(perfil.getDocumento())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilUsuarioService.save(perfil));
    }

    // HTTP PUT /api/perfiles/1 + body JSON
    // SQL: UPDATE perfiles_usuario SET documento=?, telefono=?,
    //      usuario_id=? WHERE id=1
    // Respuesta: 200 OK o 404
    @PutMapping("/{id}")
    public ResponseEntity<PerfilUsuario> update(@PathVariable Long id, @RequestBody PerfilUsuario perfil) {
        try {
            return ResponseEntity.ok(perfilUsuarioService.update(id, perfil));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP DELETE /api/perfiles/1
    // SQL: DELETE FROM perfiles_usuario WHERE id = 1
    // Respuesta: 204 No Content o 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (perfilUsuarioService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        perfilUsuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}