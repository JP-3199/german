package com.example.demo.Entity;
import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// Ya no se importan Getter y Setter de Lombok

@Entity
@Table(name = "Usuarios")
// Ya no se usan las anotaciones @Getter y @Setter a nivel de clase
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String pasword;


    // Constructor por defecto (necesario para JPA y Jackson)
    public Usuario() {
    }

    // --- Getters ---

    public Integer getId() {return id;}

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getPasword() {
        return pasword;
    }


    // --- Setters ---

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasword(String pasword) {this.pasword = pasword;}

}