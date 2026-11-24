package com.example.demo.Repository;

import com.example.demo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Spring Data JPA crea automáticamente los métodos:
    // save(usuario), findById(id), findAll(), deleteById(id)

    // Para tu filtro por nombre:
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
}