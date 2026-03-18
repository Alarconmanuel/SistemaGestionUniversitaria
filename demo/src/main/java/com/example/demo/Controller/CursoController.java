package com.example.demo.Controller;

import com.example.demo.Model.Curso;
import com.example.demo.Service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController → maneja peticiones HTTP y retorna JSON
@RestController

// @RequestMapping → todas las rutas empiezan con /api/cursos
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // HTTP GET /api/cursos
    // SQL: SELECT * FROM cursos
    // Respuesta: 200 OK + lista de cursos
    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    // HTTP GET /api/cursos/1
    // SQL: SELECT * FROM cursos WHERE id = 1
    // Respuesta: 200 OK o 404
    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {
        return cursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // HTTP GET /api/cursos/nombre/Matematicas
    // SQL: SELECT * FROM cursos WHERE nombre = 'Matematicas'
    // Respuesta: 200 OK o 404
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Curso> findByNombre(@PathVariable String nombre) {
        return cursoService.findByNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // HTTP GET /api/cursos/creditos/3
    // SQL: SELECT * FROM cursos WHERE creditos = 3
    // Respuesta: 200 OK + lista de cursos con esos créditos
    @GetMapping("/creditos/{creditos}")
    public ResponseEntity<List<Curso>> findByCreditos(@PathVariable int creditos) {
        return ResponseEntity.ok(cursoService.findByCreditos(creditos));
    }

    // HTTP POST /api/cursos + body JSON
    // SQL: INSERT INTO cursos (nombre, creditos) VALUES (?, ?)
    // Respuesta: 201 CREATED o 409 CONFLICT si ya existe el nombre
    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody Curso curso) {
        if (cursoService.existsByNombre(curso.getNombre())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
    }

    // HTTP PUT /api/cursos/1 + body JSON
    // SQL: UPDATE cursos SET nombre=?, creditos=? WHERE id=1
    // Respuesta: 200 OK o 404
    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso curso) {
        try {
            return ResponseEntity.ok(cursoService.update(id, curso));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP DELETE /api/cursos/1
    // SQL: DELETE FROM cursos WHERE id = 1
    // Respuesta: 204 No Content o 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (cursoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cursoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}