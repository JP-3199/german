package com.example.demo.Repository;

import com.example.demo.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Spring Data JPA crea automáticamente los métodos:
    // save(producto), findById(id), findAll(), deleteById(id), existsById(id)

    // Para tu filtro por nombre:
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}