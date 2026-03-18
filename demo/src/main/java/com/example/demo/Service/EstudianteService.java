package com.example.demo.Service;

import com.example.demo.Model.Estudiante;
import java.util.List;
import java.util.Optional;

public interface EstudianteService {
    // SQL: SELECT * FROM estudiantes
    List<Estudiante> findAll();

    // SQL: SELECT * FROM estudiantes WHERE id = ?
    Optional<Estudiante> findById(Long id);

    // SQL: SELECT * FROM estudiantes WHERE correo = ?
    Optional<Estudiante> findByCorreo(String correo);

    // SQL: SELECT * FROM estudiantes WHERE nombre ILIKE '%nombre%'
    List<Estudiante> findByNombreContainingIgnoreCase(String nombre);

    // SQL: INSERT INTO estudiantes (nombre, correo) VALUES (?, ?)
    Estudiante save(Estudiante estudiante);

    // SQL: UPDATE estudiantes SET nombre = ?, correo = ? WHERE id = ?
    Estudiante update(Long id, Estudiante estudiante);

    // SQL: DELETE FROM estudiantes WHERE id = ?
    void deleteById(Long id);

    // SQL: SELECT COUNT(*) > 0 FROM estudiantes WHERE correo = ?
    boolean existsByCorreo(String correo);
}