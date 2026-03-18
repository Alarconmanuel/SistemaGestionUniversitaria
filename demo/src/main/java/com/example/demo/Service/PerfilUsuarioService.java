package com.example.demo.Service;

import com.example.demo.Model.PerfilUsuario;
import java.util.List;
import java.util.Optional;

public interface PerfilUsuarioService {
    // SQL: SELECT * FROM perfiles_usuario
    List<PerfilUsuario> findAll();

    // SQL: SELECT * FROM perfiles_usuario WHERE id = ?
    Optional<PerfilUsuario> findById(Long id);

    // SQL: SELECT * FROM perfiles_usuario WHERE documento = ?
    Optional<PerfilUsuario> findByDocumento(String documento);

    // SQL: SELECT * FROM perfiles_usuario WHERE usuario_id = ?
    Optional<PerfilUsuario> findByUsuarioId(Long usuarioId);

    // SQL: INSERT INTO perfiles_usuario (documento, telefono, usuario_id)
    //      VALUES (?, ?, ?)
    PerfilUsuario save(PerfilUsuario perfil);

    // SQL: UPDATE perfiles_usuario SET documento=?, telefono=?,
    //      usuario_id=? WHERE id = ?
    PerfilUsuario update(Long id, PerfilUsuario perfil);

    // SQL: DELETE FROM perfiles_usuario WHERE id = ?
    void deleteById(Long id);

    // SQL: SELECT COUNT(*) > 0 FROM perfiles_usuario WHERE documento = ?
    boolean existsByDocumento(String documento);
}