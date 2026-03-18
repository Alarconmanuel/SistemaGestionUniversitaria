package com.example.demo.Controller;

import com.example.demo.Model.Estudiante;
import com.example.demo.Service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController → maneja peticiones HTTP y retorna JSON
@RestController

// @RequestMapping → todas las rutas empiezan con /api/estudiantes
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // HTTP GET /api/estudiantes
    // SQL: SELECT * FROM estudiantes
    // Respuesta: 200 OK + lista de estudiantes
    @GetMapping
    public ResponseEntity<List<Estudiante>> findAll() {
        return ResponseEntity.ok(estudianteService.findAll());
    }

    // HTTP GET /api/estudiantes/1
    // SQL: SELECT * FROM estudiantes WHERE id = 1
    // Respuesta: 200 OK o 404
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> findById(@PathVariable Long id) {
        return estudianteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // HTTP GET /api/estudiantes/correo/juan@mail.com
    // SQL: SELECT * FROM estudiantes WHERE correo = 'juan@mail.com'
    // Respuesta: 200 OK o 404
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Estudiante> findByCorreo(@PathVariable String correo) {
        return estudianteService.findByCorreo(correo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @RequestParam → captura parámetro de query string: ?nombre=Juan
    // HTTP GET /api/estudiantes/buscar?nombre=Juan
    // SQL: SELECT * FROM estudiantes WHERE nombre ILIKE '%Juan%'
    // Respuesta: 200 OK + lista de estudiantes que coinciden
    @GetMapping("/buscar")
    public ResponseEntity<List<Estudiante>> findByNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(estudianteService.findByNombreContainingIgnoreCase(nombre));
    }

    // HTTP POST /api/estudiantes + body JSON
    // SQL: INSERT INTO estudiantes (nombre, correo) VALUES (?, ?)
    // Respuesta: 201 CREATED o 409 CONFLICT si ya existe el correo
    @PostMapping
    public ResponseEntity<Estudiante> save(@RequestBody Estudiante estudiante) {
        if (estudianteService.existsByCorreo(estudiante.getCorreo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.save(estudiante));
    }

    // HTTP PUT /api/estudiantes/1 + body JSON
    // SQL: UPDATE estudiantes SET nombre=?, correo=? WHERE id=1
    // Respuesta: 200 OK o 404
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> update(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.ok(estudianteService.update(id, estudiante));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP DELETE /api/estudiantes/1
    // SQL: DELETE FROM estudiantes WHERE id = 1
    // Respuesta: 204 No Content o 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (estudianteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        estudianteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}