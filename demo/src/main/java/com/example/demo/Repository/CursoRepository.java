package com.example.demo.Repository;

import com.example.demo.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// @Repository → componente de acceso a datos detectado por Spring
@Repository

// JpaRepository<Curso, Long> → hereda CRUD completo sobre la tabla cursos
// SQL heredados:
// findAll()    → SELECT * FROM cursos
// findById(id) → SELECT * FROM cursos WHERE id = ?
// save(entity) → INSERT / UPDATE en cursos
// deleteById() → DELETE FROM cursos WHERE id = ?
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Metodo derivado JPA
    // SQL equivalente: SELECT * FROM cursos WHERE nombre = ?
    Optional<Curso> findByNombre(String nombre);

    // SQL equivalente: SELECT * FROM cursos WHERE creditos = ?
    List<Curso> findByCreditos(int creditos);

    // SQL equivalente: SELECT COUNT(*) > 0 FROM cursos WHERE nombre = ?
    boolean existsByNombre(String nombre);
}