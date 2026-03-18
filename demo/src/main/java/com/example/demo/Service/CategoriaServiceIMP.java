package com.example.demo.Service;

import com.example.demo.Model.Categoria;
import com.example.demo.Repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// @Service → marca esta clase como componente de lógica de negocio
// Spring la detecta y registra como Bean
// Es la capa intermedia entre Controller y Repository
@Service
public class CategoriaServiceIMP implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // Inyección de dependencia por constructor
    // Spring inyecta automáticamente el CategoriaRepository
    public CategoriaServiceIMP(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> findAll() {
        // SQL: SELECT * FROM categorias
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        // SQL: SELECT * FROM categorias WHERE id = ?
        return categoriaRepository.findById(id);
    }

    @Override
    public Optional<Categoria> findByNombre(String nombre) {
        // SQL: SELECT * FROM categorias WHERE nombre = ?
        return categoriaRepository.findByNombre(nombre);
    }

    @Override
    public Categoria save(Categoria categoria) {
        // SQL: INSERT INTO categorias (nombre, descripcion) VALUES (?, ?)
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(Long id, Categoria categoria) {
        // SQL: SELECT * FROM categorias WHERE id = ?
        // Si existe → UPDATE categorias SET nombre=?, descripcion=? WHERE id=?
        // Si no existe → lanza excepción (no ejecuta ningún SQL)
        return categoriaRepository.findById(id).map(existing -> {
            existing.setNombre(categoria.getNombre());
            existing.setDescripcion(categoria.getDescripcion());
            return categoriaRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        // SQL: DELETE FROM categorias WHERE id = ?
        categoriaRepository.deleteById(id);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        // SQL: SELECT COUNT(*) > 0 FROM categorias WHERE nombre = ?
        return categoriaRepository.existsByNombre(nombre);
    }
}