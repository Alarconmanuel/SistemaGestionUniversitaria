package com.example.demo.Repository;

import com.example.demo.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// @Repository → componente de acceso a datos detectado por Spring
@Repository

// JpaRepository<Estudiante, Long> → hereda CRUD completo sobre estudiantes
// SQL heredados:
// findAll()    → SELECT * FROM estudiantes
// findById(id) → SELECT * FROM estudiantes WHERE id = ?
// save(entity) → INSERT / UPDATE en estudiantes
// deleteById() → DELETE FROM estudiantes WHERE id = ?
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // SQL equivalente: SELECT * FROM estudiantes WHERE correo = ?
    Optional<Estudiante> findByCorreo(String correo);

    // IgnoreCase → ILIKE en PostgreSQL (sin distinguir mayúsculas)
    // Containing → LIKE '%valor%'
    // SQL equivalente:
    // SELECT * FROM estudiantes WHERE nombre ILIKE '%nombre%'
    List<Estudiante> findByNombreContainingIgnoreCase(String nombre);

    // SQL equivalente: SELECT COUNT(*) > 0 FROM estudiantes WHERE correo = ?
    boolean existsByCorreo(String correo);
}