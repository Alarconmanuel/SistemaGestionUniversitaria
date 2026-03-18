package com.example.demo.Repository;

import com.example.demo.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// @Repository → marca esta interfaz como componente de acceso a datos
// Spring la detecta automáticamente y la registra como Bean
@Repository

// JpaRepository<Categoria, Long> → hereda todos los métodos CRUD básicos
// Categoria → entidad sobre la que opera
// Long      → tipo del ID (clave primaria)
// SQL equivalentes heredados automáticamente:
// findAll()       → SELECT * FROM categorias
// findById(id)    → SELECT * FROM categorias WHERE id = ?
// save(entity)    → INSERT INTO categorias (...) VALUES (...)
//                   o UPDATE categorias SET ... WHERE id = ?
// deleteById(id)  → DELETE FROM categorias WHERE id = ?
// count()         → SELECT COUNT(*) FROM categorias
// existsById(id)  → SELECT COUNT(*) FROM categorias WHERE id = ? > 0
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Metodo derivado JPA → Spring genera el SQL automáticamente
    // basándose en el nombre del metodo
    // SQL equivalente: SELECT * FROM categorias WHERE nombre = ?
    Optional<Categoria> findByNombre(String nombre);

    // SQL equivalente: SELECT COUNT(*) > 0 FROM categorias WHERE nombre = ?
    boolean existsByNombre(String nombre);
}