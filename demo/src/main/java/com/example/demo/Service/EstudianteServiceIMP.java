package com.example.demo.Service;

import com.example.demo.Model.Estudiante;
import com.example.demo.Repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// @Service → componente de lógica de negocio registrado por Spring
@Service
public class EstudianteServiceIMP implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteServiceIMP(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public List<Estudiante> findAll() {
        // SQL: SELECT * FROM estudiantes
        return estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> findById(Long id) {
        // SQL: SELECT * FROM estudiantes WHERE id = ?
        return estudianteRepository.findById(id);
    }

    @Override
    public Optional<Estudiante> findByCorreo(String correo) {
        // SQL: SELECT * FROM estudiantes WHERE correo = ?
        return estudianteRepository.findByCorreo(correo);
    }

    @Override
    public List<Estudiante> findByNombreContainingIgnoreCase(String nombre) {
        // SQL: SELECT * FROM estudiantes WHERE nombre ILIKE '%nombre%'
        return estudianteRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        // SQL: INSERT INTO estudiantes (nombre, correo) VALUES (?, ?)
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante update(Long id, Estudiante estudiante) {
        // SQL: SELECT * FROM estudiantes WHERE id = ?
        // Si existe → UPDATE estudiantes SET nombre=?, correo=? WHERE id=?
        return estudianteRepository.findById(id).map(existing -> {
            existing.setNombre(estudiante.getNombre());
            existing.setCorreo(estudiante.getCorreo());
            existing.setCursos(estudiante.getCursos());
            return estudianteRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        // SQL: DELETE FROM estudiantes WHERE id = ?
        estudianteRepository.deleteById(id);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        // SQL: SELECT COUNT(*) > 0 FROM estudiantes WHERE correo = ?
        return estudianteRepository.existsByCorreo(correo);
    }
}