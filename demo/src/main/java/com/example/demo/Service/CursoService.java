package com.example.demo.Service;

import com.example.demo.Model.Curso;
import java.util.List;
import java.util.Optional;

public interface CursoService {
    // SQL: SELECT * FROM cursos
    List<Curso> findAll();

    // SQL: SELECT * FROM cursos WHERE id = ?
    Optional<Curso> findById(Long id);

    // SQL: SELECT * FROM cursos WHERE nombre = ?
    Optional<Curso> findByNombre(String nombre);

    // SQL: SELECT * FROM cursos WHERE creditos = ?
    List<Curso> findByCreditos(int creditos);

    // SQL: INSERT INTO cursos (nombre, creditos) VALUES (?, ?)
    Curso save(Curso curso);

    // SQL: UPDATE cursos SET nombre = ?, creditos = ? WHERE id = ?
    Curso update(Long id, Curso curso);

    // SQL: DELETE FROM cursos WHERE id = ?
    void deleteById(Long id);

    // SQL: SELECT COUNT(*) > 0 FROM cursos WHERE nombre = ?
    boolean existsByNombre(String nombre);
}