package com.example.demo.Repository;

import com.example.demo.Model.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// @Repository → componente de acceso a datos detectado por Spring
@Repository

// JpaRepository<PerfilUsuario, Long> → hereda CRUD completo sobre perfiles
// SQL heredados:
// findAll()    → SELECT * FROM perfiles_usuario
// findById(id) → SELECT * FROM perfiles_usuario WHERE id = ?
// save(entity) → INSERT / UPDATE en perfiles_usuario
// deleteById() → DELETE FROM perfiles_usuario WHERE id = ?
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long> {

    // SQL equivalente:
    // SELECT * FROM perfiles_usuario WHERE documento = ?
    Optional<PerfilUsuario> findByDocumento(String documento);

    // Navega la relación OneToOne PerfilUsuario → Usuario
    // SQL equivalente:
    // SELECT * FROM perfiles_usuario WHERE usuario_id = ?
    Optional<PerfilUsuario> findByUsuarioId(Long usuarioId);

    // SQL equivalente:
    // SELECT COUNT(*) > 0 FROM perfiles_usuario WHERE documento = ?
    boolean existsByDocumento(String documento);
}