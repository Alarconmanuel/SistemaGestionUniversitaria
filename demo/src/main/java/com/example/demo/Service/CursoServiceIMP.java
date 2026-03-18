package com.example.demo.Service;

import com.example.demo.Model.Curso;
import com.example.demo.Repository.CursoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// @Service → componente de lógica de negocio registrado por Spring
@Service
public class CursoServiceIMP implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceIMP(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Curso> findAll() {
        // SQL: SELECT * FROM cursos
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> findById(Long id) {
        // SQL: SELECT * FROM cursos WHERE id = ?
        return cursoRepository.findById(id);
    }

    @Override
    public Optional<Curso> findByNombre(String nombre) {
        // SQL: SELECT * FROM cursos WHERE nombre = ?
        return cursoRepository.findByNombre(nombre);
    }

    @Override
    public List<Curso> findByCreditos(int creditos) {
        // SQL: SELECT * FROM cursos WHERE creditos = ?
        return cursoRepository.findByCreditos(creditos);
    }

    @Override
    public Curso save(Curso curso) {
        // SQL: INSERT INTO cursos (nombre, creditos) VALUES (?, ?)
        return cursoRepository.save(curso);
    }

    @Override
    public Curso update(Long id, Curso curso) {
        // SQL: SELECT * FROM cursos WHERE id = ?
        // Si existe → UPDATE cursos SET nombre=?, creditos=? WHERE id=?
        return cursoRepository.findById(id).map(existing -> {
            existing.setNombre(curso.getNombre());
            existing.setCreditos(curso.getCreditos());
            return cursoRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        // SQL: DELETE FROM cursos WHERE id = ?
        cursoRepository.deleteById(id);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        // SQL: SELECT COUNT(*) > 0 FROM cursos WHERE nombre = ?
        return cursoRepository.existsByNombre(nombre);
    }
}