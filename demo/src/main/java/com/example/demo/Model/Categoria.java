package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// @Entity → le dice a JPA que esta clase es una tabla en la base de datos
// SQL equivalente: CREATE TABLE categorias (...)
@Entity

// @Table(name="categorias") → define el nombre exacto de la tabla en la BD
// SQL equivalente: CREATE TABLE categorias
@Table(name="categorias")
public class Categoria {

    // @Id → define la clave primaria de la tabla
    // SQL equivalente: id BIGINT PRIMARY KEY
    @Id

    // @GeneratedValue(strategy = GenerationType.IDENTITY) → el valor del ID
    // lo genera automáticamente la base de datos (autoincremental)
    // SQL equivalente: id BIGINT GENERATED ALWAYS AS IDENTITY
    // o en PostgreSQL: id SERIAL PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable = false, unique = true, length = 80)
    // nullable = false → SQL: nombre VARCHAR(80) NOT NULL
    // unique = true    → SQL: UNIQUE(nombre)
    // length = 80      → SQL: VARCHAR(80)
    @Column(nullable = false, unique = true, length = 80)
    private String nombre;

    // nullable = false → NOT NULL
    // length = 150     → VARCHAR(150)
    // SQL equivalente: descripcion VARCHAR(150) NOT NULL
    @Column(nullable = false, length = 150)
    private String descripcion;

    // @OneToMany → relación uno a muchos
    // Una Categoria puede tener muchos Libros
    // SQL equivalente: JOIN categorias c ON c.id = l.categoria_id
    // mappedBy = "categoria" → el lado dueño de la relación es Libro
    // cascade = CascadeType.ALL → si se elimina/actualiza Categoria,
    //           se propaga la operación a sus Libros
    //           SQL equivalente: ON DELETE CASCADE
    // orphanRemoval = false → si un Libro se desvincula de Categoria,
    //                         NO se elimina de la BD
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = false)

    // @JsonManagedReference → evita bucle infinito al serializar JSON
    // en la relación bidireccional Categoria ↔ Libro
    @JsonManagedReference
    private List<Libro> libros = new ArrayList<>();

    public Categoria() {}

    public Categoria(List<Libro> libros, String descripcion, String nombre, Long id) {
        this.libros = libros;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.id = id;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public List<Libro> getLibros() { return libros; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", libros=" + libros + '}';
    }
}