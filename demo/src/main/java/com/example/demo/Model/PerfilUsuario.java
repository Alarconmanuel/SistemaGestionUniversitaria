package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

// @Entity → esta clase representa la tabla "perfiles_usuario" en la BD
// SQL equivalente: CREATE TABLE perfiles_usuario (...)
@Entity

// @Table(name = "perfiles_usuario") → nombre exacto de la tabla
// SQL equivalente: CREATE TABLE perfiles_usuario
@Table(name = "perfiles_usuario")
public class PerfilUsuario {

    // @Id → clave primaria
    // SQL equivalente: id BIGINT PRIMARY KEY
    @Id

    // ID autoincremental generado por la BD
    // SQL equivalente: id SERIAL PRIMARY KEY (PostgreSQL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable = false → NOT NULL
    // unique = true    → UNIQUE
    // length = 120     → VARCHAR(120)
    // SQL equivalente: documento VARCHAR(120) NOT NULL UNIQUE
    @Column(nullable = false, unique = true, length = 120)
    private String documento;

    // nullable = false → NOT NULL
    // length = 20      → VARCHAR(20)
    // SQL equivalente: telefono VARCHAR(20) NOT NULL
    @Column(nullable = false, length = 20)
    private String telefono;

    // @OneToOne → relación uno a uno con Usuario
    // Un PerfilUsuario pertenece a exactamente un Usuario
    // optional = false → la relación es obligatoria,
    //                    todo perfil debe tener un usuario
    @OneToOne(optional = false)

    // @JoinColumn → define la columna FK en la tabla perfiles_usuario
    // name = "usuario_id"  → nombre de la columna FK
    // nullable = false     → NOT NULL
    // unique = true        → garantiza que un usuario solo tenga un perfil
    // SQL equivalente:
    // usuario_id BIGINT NOT NULL UNIQUE REFERENCES usuarios(id)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)

    // @JsonIgnoreProperties("perfil") → al serializar Usuario dentro
    // de PerfilUsuario, ignora el campo "perfil" para evitar bucle infinito
    @JsonIgnoreProperties("perfil")
    private Usuario usuario;

    public PerfilUsuario() {}

    public PerfilUsuario(Long id, String documento, String telefono, Usuario usuario) {
        this.id = id;
        this.documento = documento;
        this.telefono = telefono;
        this.usuario = usuario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @Override
    public String toString() {
        return "PerfilUsuario{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", usuario=" + usuario + '}';
    }
}