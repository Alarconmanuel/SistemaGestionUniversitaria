package com.example.demo.Controller;

import com.example.demo.Model.Usuario;
import com.example.demo.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController → maneja peticiones HTTP y retorna JSON
@RestController

// @RequestMapping → todas las rutas empiezan con /api/usuarios
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // HTTP GET /api/usuarios
    // SQL: SELECT * FROM usuarios
    // Respuesta: 200 OK + lista de usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    // HTTP GET /api/usuarios/1
    // SQL: SELECT * FROM usuarios WHERE id = 1
    // Respuesta: 200 OK o 404
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // HTTP GET /api/usuarios/correo/ana@mail.com
    // SQL: SELECT * FROM usuarios WHERE correo = 'ana@mail.com'
    // Respuesta: 200 OK o 404
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Usuario> findByCorreo(@PathVariable String correo) {
        return usuarioService.findByCorreo(correo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @RequestParam → captura parámetro de query string: ?nombre=Ana
    // HTTP GET /api/usuarios/buscar?nombre=Ana
    // SQL: SELECT * FROM usuarios WHERE nombre ILIKE '%Ana%'
    // Respuesta: 200 OK + lista de usuarios que coinciden
    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> findByNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(usuarioService.findByNombreContainingIgnoreCase(nombre));
    }

    // HTTP POST /api/usuarios + body JSON
    // SQL: INSERT INTO usuarios (nombre, correo) VALUES (?, ?)
    // Respuesta: 201 CREATED o 409 CONFLICT si ya existe el correo
    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        if (usuarioService.existsByCorreo(usuario.getCorreo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    // HTTP PUT /api/usuarios/1 + body JSON
    // SQL: UPDATE usuarios SET nombre=?, correo=? WHERE id=1
    // Respuesta: 200 OK o 404
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(usuarioService.update(id, usuario));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP DELETE /api/usuarios/1
    // SQL: DELETE FROM usuarios WHERE id = 1
    // Respuesta: 204 No Content o 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (usuarioService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}