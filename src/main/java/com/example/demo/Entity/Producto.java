package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer precio;

    private String calificacion;

    private String descripcion;

    // Constructor por defecto obligatorio para JPA y Jackson
    public Producto() {
    }

    public Producto(String nombre, Integer precio, String calificacion, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.calificacion = calificacion;
        this.descripcion = descripcion;
    }

    // --- Getters ---
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // --- Setters ---
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}