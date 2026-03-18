package com.example.demo.Service;

import com.example.demo.Model.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroService {
    // SQL: SELECT * FROM libros
    List<Libro> findAll();

    // SQL: SELECT * FROM libros WHERE id = ?
    Optional<Libro> findById(Long id);

    // SQL: SELECT * FROM libros WHERE categoria_id = ?
    List<Libro> findByCategoriaId(Long categoriaId);

    // SQL: SELECT * FROM libros WHERE autor = ?
    List<Libro> findByAutor(String autor);

    // SQL: SELECT * FROM libros WHERE titulo ILIKE '%titulo%'
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // SQL: SELECT * FROM libros WHERE anio_publicacion = ?
    List<Libro> findByPublicacion(String publicacion);

    // SQL: INSERT INTO libros (titulo, autor, anio_publicacion, categoria_id)
    //      VALUES (?, ?, ?, ?)
    Libro save(Libro libro);

    // SQL: UPDATE libros SET titulo=?, autor=?, anio_publicacion=?,
    //      categoria_id=? WHERE id = ?
    Libro update(Long id, Libro libro);

    // SQL: DELETE FROM libros WHERE id = ?
    void deleteById(Long id);
}