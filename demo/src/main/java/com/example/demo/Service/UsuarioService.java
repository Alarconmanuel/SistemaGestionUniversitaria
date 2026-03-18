package com.example.demo.Service;

import com.example.demo.Model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    // SQL: SELECT * FROM usuarios
    List<Usuario> findAll();

    // SQL: SELECT * FROM usuarios WHERE id = ?
    Optional<Usuario> findById(Long id);

    // SQL: SELECT * FROM usuarios WHERE correo = ?
    Optional<Usuario> findByCorreo(String correo);

    // SQL: SELECT * FROM usuarios WHERE nombre ILIKE '%nombre%'
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);

    // SQL: INSERT INTO usuarios (nombre, correo) VALUES (?, ?)
    Usuario save(Usuario usuario);

    // SQL: UPDATE usuarios SET nombre = ?, correo = ? WHERE id = ?
    Usuario update(Long id, Usuario usuario);

    // SQL: DELETE FROM usuarios WHERE id = ?
    void deleteById(Long id);

    // SQL: SELECT COUNT(*) > 0 FROM usuarios WHERE correo = ?
    boolean existsByCorreo(String correo);
}