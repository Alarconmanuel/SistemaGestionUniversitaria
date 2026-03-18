package com.example.demo.Service;

import com.example.demo.Model.Libro;
import com.example.demo.Repository.LibroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// @Service → componente de lógica de negocio registrado por Spring
@Service
public class LibroServiceIMP implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceIMP(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> findAll() {
        // SQL: SELECT * FROM libros
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> findById(Long id) {
        // SQL: SELECT * FROM libros WHERE id = ?
        return libroRepository.findById(id);
    }

    @Override
    public List<Libro> findByCategoriaId(Long categoriaId) {
        // SQL: SELECT * FROM libros WHERE categoria_id = ?
        return libroRepository.findByCategoriaId(categoriaId);
    }

    @Override
    public List<Libro> findByAutor(String autor) {
        // SQL: SELECT * FROM libros WHERE autor = ?
        return libroRepository.findByAutor(autor);
    }

    @Override
    public List<Libro> findByTituloContainingIgnoreCase(String titulo) {
        // SQL: SELECT * FROM libros WHERE titulo ILIKE '%titulo%'
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<Libro> findByPublicacion(String publicacion) {
        // SQL: SELECT * FROM libros WHERE anio_publicacion = ?
        return libroRepository.findByPublicacion(publicacion);
    }

    @Override
    public Libro save(Libro libro) {
        // SQL: INSERT INTO libros (titulo, autor, anio_publicacion, categoria_id)
        //      VALUES (?, ?, ?, ?)
        return libroRepository.save(libro);
    }

    @Override
    public Libro update(Long id, Libro libro) {
        // SQL: SELECT * FROM libros WHERE id = ?
        // Si existe → UPDATE libros SET titulo=?, autor=?,
        //             anio_publicacion=?, categoria_id=? WHERE id=?
        return libroRepository.findById(id).map(existing -> {
            existing.setTitulo(libro.getTitulo());
            existing.setAutor(libro.getAutor());
            existing.setPublicacion(libro.getPublicacion());
            existing.setCategoria(libro.getCategoria());
            return libroRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        // SQL: DELETE FROM libros WHERE id = ?
        libroRepository.deleteById(id);
    }
}