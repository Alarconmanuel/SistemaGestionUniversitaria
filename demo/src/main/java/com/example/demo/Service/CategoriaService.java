package com.example.demo.Service;

import com.example.demo.Model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    // SQL: SELECT * FROM categorias
    List<Categoria> findAll();

    // SQL: SELECT * FROM categorias WHERE id = ?
    Optional<Categoria> findById(Long id);

    // SQL: SELECT * FROM categorias WHERE nombre = ?
    Optional<Categoria> findByNombre(String nombre);

    // SQL: INSERT INTO categorias (nombre, descripcion) VALUES (?, ?)
    Categoria save(Categoria categoria);

    // SQL: UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ?
    Categoria update(Long id, Categoria categoria);

    // SQL: DELETE FROM categorias WHERE id = ?
    void deleteById(Long id);

    // SQL: SELECT COUNT(*) > 0 FROM categorias WHERE nombre = ?
    boolean existsByNombre(String nombre);
}