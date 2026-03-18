package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

// @Entity → esta clase representa la tabla "usuarios" en la BD
// SQL equivalente: CREATE TABLE usuarios (...)
@Entity

// @Table(name = "usuarios") → nombre exacto de la tabla
// SQL equivalente: CREATE TABLE usuarios
@Table(name = "usuarios")
public class Usuario {

    // @Id → clave primaria
    // SQL equivalente: id BIGINT PRIMARY KEY
    @Id

    // ID autoincremental generado por la BD
    // SQL equivalente: id SERIAL PRIMARY KEY (PostgreSQL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable = false → NOT NULL
    // length = 80      → VARCHAR(80)
    // SQL equivalente: nombre VARCHAR(80) NOT NULL
    @Column(nullable = false, length = 80)
    private String nombre;

    // nullable = false → NOT NULL
    // unique = true    → UNIQUE
    // length = 120     → VARCHAR(120)
    // SQL equivalente: correo VARCHAR(120) NOT NULL UNIQUE
    @Column(nullable = false, unique = true, length = 120)
    private String correo;

    // @OneToOne(mappedBy = "usuario") → relación uno a uno con PerfilUsuario
    // mappedBy = "usuario" → el dueño de la relación es PerfilUsuario
    //                        (la FK está en la tabla perfiles_usuario)
    // cascade = CascadeType.ALL → si se elimina/actualiza Usuario,
    //           se propaga a su PerfilUsuario
    //           SQL equivalente: ON DELETE CASCADE
    // SQL equivalente:
    // SELECT u.*, p.* FROM usuarios u
    // JOIN perfiles_usuario p ON p.usuario_id = u.id
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)

    // @JsonIgnoreProperties("usuario") → al serializar PerfilUsuario
    // dentro de Usuario, ignora el campo "usuario" para evitar bucle infinito
    @JsonIgnoreProperties("usuario")
    private PerfilUsuario perfil;

    // Constructor vacío obligatorio para JPA
    // JPA lo necesita para instanciar la entidad al leer de la BD
    public Usuario() {}

    // @JsonCreator → le dice a Jackson que use este constructor
    // para deserializar JSON a objeto Usuario
    // Permite que Jackson construya Usuario desde {"id": 1}
    // sin necesitar todos los campos
    @JsonCreator
    public Usuario(
            // @JsonProperty → mapea cada campo del JSON al parámetro
            @JsonProperty("id") Long id,
            @JsonProperty("nombre") String nombre,
            @JsonProperty("correo") String correo,
            @JsonProperty("perfil") PerfilUsuario perfil) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.perfil = perfil;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public PerfilUsuario getPerfil() { return perfil; }
    public void setPerfil(PerfilUsuario perfil) { this.perfil = perfil; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", perfil=" + perfil + '}';
    }
}