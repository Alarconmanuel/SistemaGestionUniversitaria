package com.example.demo.Controller;

import com.example.demo.Model.Libro;
import com.example.demo.Service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController → maneja peticiones HTTP y retorna JSON
@RestController

// @RequestMapping → todas las rutas empiezan con /api/libros
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // HTTP GET /api/libros
    // SQL: SELECT * FROM libros
    // Respuesta: 200 OK + lista de libros
    @GetMapping
    public ResponseEntity<List<Libro>> findAll() {
        return ResponseEntity.ok(libroService.findAll());
    }

    // HTTP GET /api/libros/1
    // SQL: SELECT * FROM libros WHERE id = 1
    // Respuesta: 200 OK o 404
    @GetMapping("/{id}")
    public ResponseEntity<Libro> findById(@PathVariable Long id) {
        return libroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // HTTP GET /api/libros/categoria/2
    // SQL: SELECT * FROM libros WHERE categoria_id = 2
    // Respuesta: 200 OK + lista de libros de esa categoría
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Libro>> findByCategoriaId(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(libroService.findByCategoriaId(categoriaId));
    }

    // HTTP GET /api/libros/autor/García Márquez
    // SQL: SELECT * FROM libros WHERE autor = 'García Márquez'
    // Respuesta: 200 OK + lista de libros de ese autor
    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<Libro>> findByAutor(@PathVariable String autor) {
        return ResponseEntity.ok(libroService.findByAutor(autor));
    }

    // @RequestParam → captura parámetro de query string: ?titulo=Harry
    // HTTP GET /api/libros/buscar?titulo=Harry
    // SQL: SELECT * FROM libros WHERE titulo ILIKE '%Harry%'
    // Respuesta: 200 OK + lista de libros que coinciden
    @GetMapping("/buscar")
    public ResponseEntity<List<Libro>> findByTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(libroService.findByTituloContainingIgnoreCase(titulo));
    }

    // HTTP GET /api/libros/publicacion/2001
    // SQL: SELECT * FROM libros WHERE anio_publicacion = '2001'
    // Respuesta: 200 OK + lista de libros de ese año
    @GetMapping("/publicacion/{publicacion}")
    public ResponseEntity<List<Libro>> findByPublicacion(@PathVariable String publicacion) {
        return ResponseEntity.ok(libroService.findByPublicacion(publicacion));
    }

    // HTTP POST /api/libros + body JSON
    // SQL: INSERT INTO libros (titulo, autor, anio_publicacion, categoria_id)
    //      VALUES (?, ?, ?, ?)
    // Respuesta: 201 CREATED + libro creado
    @PostMapping
    public ResponseEntity<Libro> save(@RequestBody Libro libro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libroService.save(libro));
    }

    // HTTP PUT /api/libros/1 + body JSON
    // SQL: UPDATE libros SET titulo=?, autor=?,
    //      anio_publicacion=?, categoria_id=? WHERE id=1
    // Respuesta: 200 OK o 404
    @PutMapping("/{id}")
    public ResponseEntity<Libro> update(@PathVariable Long id, @RequestBody Libro libro) {
        try {
            return ResponseEntity.ok(libroService.update(id, libro));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP DELETE /api/libros/1
    // SQL: DELETE FROM libros WHERE id = 1
    // Respuesta: 204 No Content o 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (libroService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        libroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}