package com.example.demo.Service;

import com.example.demo.Model.PerfilUsuario;
import com.example.demo.Repository.PerfilUsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// @Service → componente de lógica de negocio registrado por Spring
@Service
public class PerfilUsuarioServiceIMP implements PerfilUsuarioService {

    private final PerfilUsuarioRepository perfilUsuarioRepository;

    public PerfilUsuarioServiceIMP(PerfilUsuarioRepository perfilUsuarioRepository) {
        this.perfilUsuarioRepository = perfilUsuarioRepository;
    }

    @Override
    public List<PerfilUsuario> findAll() {
        // SQL: SELECT * FROM perfiles_usuario
        return perfilUsuarioRepository.findAll();
    }

    @Override
    public Optional<PerfilUsuario> findById(Long id) {
        // SQL: SELECT * FROM perfiles_usuario WHERE id = ?
        return perfilUsuarioRepository.findById(id);
    }

    @Override
    public Optional<PerfilUsuario> findByDocumento(String documento) {
        // SQL: SELECT * FROM perfiles_usuario WHERE documento = ?
        return perfilUsuarioRepository.findByDocumento(documento);
    }

    @Override
    public Optional<PerfilUsuario> findByUsuarioId(Long usuarioId) {
        // SQL: SELECT * FROM perfiles_usuario WHERE usuario_id = ?
        return perfilUsuarioRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public PerfilUsuario save(PerfilUsuario perfil) {
        // SQL: INSERT INTO perfiles_usuario (documento, telefono, usuario_id)
        //      VALUES (?, ?, ?)
        return perfilUsuarioRepository.save(perfil);
    }

    @Override
    public PerfilUsuario update(Long id, PerfilUsuario perfil) {
        // SQL: SELECT * FROM perfiles_usuario WHERE id = ?
        // Si existe → UPDATE perfiles_usuario SET documento=?,
        //             telefono=?, usuario_id=? WHERE id=?
        return perfilUsuarioRepository.findById(id).map(existing -> {
            existing.setDocumento(perfil.getDocumento());
            existing.setTelefono(perfil.getTelefono());
            existing.setUsuario(perfil.getUsuario());
            return perfilUsuarioRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("PerfilUsuario no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        // SQL: DELETE FROM perfiles_usuario WHERE id = ?
        perfilUsuarioRepository.deleteById(id);
    }

    @Override
    public boolean existsByDocumento(String documento) {
        // SQL: SELECT COUNT(*) > 0 FROM perfiles_usuario WHERE documento = ?
        return perfilUsuarioRepository.existsByDocumento(documento);
    }
}