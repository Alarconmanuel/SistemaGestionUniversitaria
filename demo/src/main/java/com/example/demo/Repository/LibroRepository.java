package com.example.demo.Repository;

import com.example.demo.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// @Repository → componente de acceso a datos detectado por Spring
@Repository

// JpaRepository<Libro, Long> → hereda CRUD completo sobre libros
// SQL heredados:
// findAll()    → SELECT * FROM libros
// findById(id) → SELECT * FROM libros WHERE id = ?
// save(entity) → INSERT / UPDATE en libros
// deleteById() → DELETE FROM libros WHERE id = ?
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Navega la relación ManyToOne Libro → Categoria
    // SQL equivalente:
    // SELECT * FROM libros WHERE categoria_id = ?
    List<Libro> findByCategoriaId(Long categoriaId);

    // SQL equivalente: SELECT * FROM libros WHERE autor = ?
    List<Libro> findByAutor(String autor);

    // Containing + IgnoreCase → búsqueda parcial sin distinguir mayúsculas
    // SQL equivalente:
    // SELECT * FROM libros WHERE titulo ILIKE '%titulo%'
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // SQL equivalente: SELECT * FROM libros WHERE anio_publicacion = ?
    List<Libro> findByPublicacion(String publicacion);
}