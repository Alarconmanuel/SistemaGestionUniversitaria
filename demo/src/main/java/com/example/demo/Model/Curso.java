package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Set;

// @Entity → esta clase representa la tabla "cursos" en la BD
// SQL equivalente: CREATE TABLE cursos (...)
@Entity

// @Table(name = "cursos") → nombre de la tabla en la BD
// SQL equivalente: CREATE TABLE cursos
@Table(name = "cursos")
public class Curso {

    // @Id → clave primaria de la tabla
    // SQL equivalente: id BIGINT PRIMARY KEY
    @Id

    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID autoincremental generado por la BD
    // SQL equivalente: id SERIAL PRIMARY KEY (PostgreSQL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable = false → NOT NULL
    // unique = true    → UNIQUE
    // length = 100     → VARCHAR(100)
    // SQL equivalente: nombre VARCHAR(100) NOT NULL UNIQUE
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    // nullable = false → NOT NULL
    // length = 20      → en enteros no aplica longitud real,
    //                    pero JPA lo usa como referencia
    // SQL equivalente: creditos INT NOT NULL
    @Column(nullable = false, length = 20)
    private int creditos;

    // @ManyToMany(mappedBy = "cursos") → relación muchos a muchos
    // Un Curso puede tener muchos Estudiantes
    // y un Estudiante puede tener muchos Cursos
    // mappedBy = "cursos" → el dueño de la relación es Estudiante
    // SQL equivalente: tabla intermedia estudiante_curso
    // SELECT * FROM cursos c
    // JOIN estudiante_curso ec ON ec.curso_id = c.id
    // JOIN estudiantes e ON e.id = ec.estudiante_id
    @ManyToMany(mappedBy = "cursos")

    // @JsonIgnoreProperties("cursos") → al serializar Estudiante
    // dentro de Curso, ignora el campo "cursos" para evitar
    // bucle infinito en el JSON
    @JsonIgnoreProperties("cursos")
    private Set<Estudiante> estudiantes = new java.util.HashSet<>();

    public Curso() {}

    public Curso(Long id, String nombre, int creditos, Set<Estudiante> estudiantes) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.estudiantes = estudiantes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }
    public Set<Estudiante> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(Set<Estudiante> estudiantes) { this.estudiantes = estudiantes; }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ", estudiantes=" + estudiantes + '}';
    }
}