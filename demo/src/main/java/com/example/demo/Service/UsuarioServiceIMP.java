package com.example.demo.Service;

import com.example.demo.Model.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// @Service → componente de lógica de negocio registrado por Spring
@Service
public class UsuarioServiceIMP implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceIMP(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAll() {
        // SQL: SELECT * FROM usuarios
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        // SQL: SELECT * FROM usuarios WHERE id = ?
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        // SQL: SELECT * FROM usuarios WHERE correo = ?
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public List<Usuario> findByNombreContainingIgnoreCase(String nombre) {
        // SQL: SELECT * FROM usuarios WHERE nombre ILIKE '%nombre%'
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Usuario save(Usuario usuario) {
        // SQL: INSERT INTO usuarios (nombre, correo) VALUES (?, ?)
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Long id, Usuario usuario) {
        // SQL: SELECT * FROM usuarios WHERE id = ?
        // Si existe → UPDATE usuarios SET nombre=?, correo=? WHERE id=?
        return usuarioRepository.findById(id).map(existing -> {
            existing.setNombre(usuario.getNombre());
            existing.setCorreo(usuario.getCorreo());
            return usuarioRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        // SQL: DELETE FROM usuarios WHERE id = ?
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        // SQL: SELECT COUNT(*) > 0 FROM usuarios WHERE correo = ?
        return usuarioRepository.existsByCorreo(correo);
    }
}