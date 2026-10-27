package com.example.demo;

import java.util.UUID;
public class Producto {
    private String id;
    private String nombre;
    private int precio;
    private String calificacion;
    private String descripcion;
    public Producto() { this.id = UUID.randomUUID().toString();}
    public Producto(String nombre, int precio, String calificacion, String descripcion){
        this.id = UUID.randomUUID().toString();
        this.nombre=nombre;
        this.precio=precio;
        this.calificacion=calificacion;
        this.descripcion = descripcion;
    }
    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}