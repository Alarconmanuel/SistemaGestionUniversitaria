package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Set;

// @Entity → esta clase representa la tabla "estudiantes" en la BD
// SQL equivalente: CREATE TABLE estudiantes (...)
@Entity

// @Table(name = "estudiantes") → nombre exacto de la tabla
// SQL equivalente: CREATE TABLE estudiantes
@Table(name = "estudiantes")
public class Estudiante {

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

    // @ManyToMany → relación muchos a muchos con Curso
    // Un Estudiante puede tener muchos Cursos
    // y un Curso puede tener muchos Estudiantes
    @ManyToMany

    // @JoinTable → define la tabla intermedia de la relación
    // SQL equivalente:
    // CREATE TABLE estudiante_curso (
    //     estudiante_id BIGINT REFERENCES estudiantes(id),
    //     curso_id      BIGINT REFERENCES cursos(id),
    //     PRIMARY KEY (estudiante_id, curso_id)
    // )
    @JoinTable(
            name = "estudiante_curso",
            // joinColumns → columna que referencia a esta entidad (Estudiante)
            // SQL: estudiante_id BIGINT REFERENCES estudiantes(id)
            joinColumns = @JoinColumn(name = "estudiante_id"),
            // inverseJoinColumns → columna que referencia a la otra entidad (Curso)
            // SQL: curso_id BIGINT REFERENCES cursos(id)
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )

    // @JsonProperty("estudiantes") → nombre del campo en el JSON de respuesta
    @JsonProperty("estudiantes")
    private Set<Curso> cursos = new java.util.HashSet<>();

    public Estudiante() {}

    public Estudiante(Long id, String nombre, String correo, Set<Curso> cursos) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.cursos = cursos;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public Set<Curso> getCursos() { return cursos; }
    public void setCursos(Set<Curso> cursos) { this.cursos = cursos; }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", cursos=" + cursos + '}';
    }
}