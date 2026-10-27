package com.example.demo;
import org.springframework.web.server.handler.DefaultWebFilterChain;

import java.util.UUID;
public class stock {
    private String nombre;
    private int precio;
    private String calificacion;
    private     String Descripcion ;
    public stock() { this.nombre = UUID.randomUUID().toString();
    }
    // Getters y Setters
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
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = Descripcion;
    }
}