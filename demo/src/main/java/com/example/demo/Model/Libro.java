package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

// @Entity → esta clase representa la tabla "libros" en la BD
// SQL equivalente: CREATE TABLE libros (...)
@Entity

// @Table(name = "libros") → nombre exacto de la tabla
// SQL equivalente: CREATE TABLE libros
@Table(name = "libros")
public class Libro {

    // @Id → clave primaria
    // SQL equivalente: id BIGINT PRIMARY KEY
    @Id

    // ID autoincremental generado por la BD
    // SQL equivalente: id SERIAL PRIMARY KEY (PostgreSQL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable = false → NOT NULL
    // length = 120     → VARCHAR(120)
    // SQL equivalente: titulo VARCHAR(120) NOT NULL
    @Column(nullable = false, length = 120)
    private String titulo;

    // nullable = false → NOT NULL
    // length = 100     → VARCHAR(100)
    // SQL equivalente: autor VARCHAR(100) NOT NULL
    @Column(nullable = false, length = 100)
    private String autor;

    // name = "anio_publicacion" → nombre real de la columna en la BD
    // nullable = false          → NOT NULL
    // length = 120              → VARCHAR(120)
    // SQL equivalente: anio_publicacion VARCHAR(120) NOT NULL
    @Column(name = "anio_publicacion", nullable = false, length = 120)
    private String publicacion;

    // @ManyToOne → relación muchos a uno con Categoria
    // Muchos Libros pertenecen a una sola Categoria
    // optional = false → la relación es obligatoria (NOT NULL)
    // fetch = FetchType.EAGER → carga la Categoria junto con el Libro
    //                           inmediatamente en la misma consulta
    // SQL equivalente:
    // SELECT l.*, c.* FROM libros l
    // JOIN categorias c ON c.id = l.categoria_id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)

    // @JoinColumn → define la columna de clave foránea en la tabla libros
    // name = "categoria_id" → nombre de la columna FK
    // nullable = false      → NOT NULL
    // SQL equivalente: categoria_id BIGINT NOT NULL REFERENCES categorias(id)
    @JoinColumn(name = "categoria_id", nullable = false)

    // @JsonBackReference → lado inverso de @JsonManagedReference en Categoria
    // evita el bucle infinito JSON: Categoria → Libros → Categoria → ...
    @JsonBackReference
    private Categoria categoria;

    public Libro() {}

    public Libro(Long id, String titulo, String autor, String publicacion, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.publicacion = publicacion;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getPublicacion() { return publicacion; }
    public void setPublicacion(String publicacion) { this.publicacion = publicacion; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", publicacion='" + publicacion + '\'' +
                ", categoria=" + categoria + '}';
    }
}