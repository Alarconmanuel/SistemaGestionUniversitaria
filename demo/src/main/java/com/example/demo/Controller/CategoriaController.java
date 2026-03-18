package com.example.demo.Controller;

import com.example.demo.Model.Categoria;
import com.example.demo.Service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController → combina @Controller + @ResponseBody
// Indica que esta clase maneja peticiones HTTP
// y retorna directamente JSON (no vistas HTML)
@RestController

// @RequestMapping("/api/categorias") → prefijo base de todas las rutas
// de este controller
// Equivale a: todas las URLs empiezan con /api/categorias
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    // Inyección de dependencia por constructor
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // @GetMapping → maneja peticiones HTTP GET
    // HTTP GET /api/categorias
    // SQL: SELECT * FROM categorias
    // Respuesta: 200 OK + lista de categorías en JSON
    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    // @PathVariable → captura el {id} de la URL
    // HTTP GET /api/categorias/1
    // SQL: SELECT * FROM categorias WHERE id = 1
    // Respuesta: 200 OK + categoría en JSON, o 404 si no existe
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id) {
        return categoriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // HTTP GET /api/categorias/nombre/Ciencia
    // SQL: SELECT * FROM categorias WHERE nombre = 'Ciencia'
    // Respuesta: 200 OK + categoría en JSON, o 404 si no existe
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Categoria> findByNombre(@PathVariable String nombre) {
        return categoriaService.findByNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @PostMapping → maneja peticiones HTTP POST
    // @RequestBody → convierte el JSON del body de la petición en objeto Java
    // HTTP POST /api/categorias + body JSON
    // SQL: INSERT INTO categorias (nombre, descripcion) VALUES (?, ?)
    // Respuesta: 201 CREATED + categoría creada, o 409 CONFLICT si ya existe
    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
        if (categoriaService.existsByNombre(categoria.getNombre())) {
            // 409 Conflict → ya existe un registro con ese nombre
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        // 201 Created → registro insertado exitosamente
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoria));
    }

    // @PutMapping → maneja peticiones HTTP PUT (actualización completa)
    // HTTP PUT /api/categorias/1 + body JSON
    // SQL: UPDATE categorias SET nombre=?, descripcion=? WHERE id=1
    // Respuesta: 200 OK + categoría actualizada, o 404 si no existe
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok(categoriaService.update(id, categoria));
        } catch (RuntimeException e) {
            // 404 Not Found → no existe el registro con ese id
            return ResponseEntity.notFound().build();
        }
    }

    // @DeleteMapping → maneja peticiones HTTP DELETE
    // HTTP DELETE /api/categorias/1
    // SQL: DELETE FROM categorias WHERE id = 1
    // Respuesta: 204 No Content si se eliminó, o 404 si no existe
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (categoriaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.deleteById(id);
        // 204 No Content → eliminado exitosamente, sin cuerpo en la respuesta
        return ResponseEntity.noContent().build();
    }
}