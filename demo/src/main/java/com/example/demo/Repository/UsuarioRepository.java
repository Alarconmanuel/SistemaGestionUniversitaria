package com.example.demo.Repository;

import com.example.demo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// @Repository → componente de acceso a datos detectado por Spring
@Repository

// JpaRepository<Usuario, Long> → hereda CRUD completo sobre usuarios
// SQL heredados:
// findAll()    → SELECT * FROM usuarios
// findById(id) → SELECT * FROM usuarios WHERE id = ?
// save(entity) → INSERT / UPDATE en usuarios
// deleteById() → DELETE FROM usuarios WHERE id = ?
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // SQL equivalente: SELECT * FROM usuarios WHERE correo = ?
    Optional<Usuario> findByCorreo(String correo);

    // SQL equivalente:
    // SELECT * FROM usuarios WHERE nombre ILIKE '%nombre%'
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);

    // SQL equivalente: SELECT COUNT(*) > 0 FROM usuarios WHERE correo = ?
    boolean existsByCorreo(String correo);
}