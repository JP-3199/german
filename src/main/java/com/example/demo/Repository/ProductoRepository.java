package com.example.demo.Repository;

import com.example.demo.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Spring Data JPA ya te da gratis: save(), findById(), findAll(), deleteById()

    // Consulta personalizada para filtrar por nombre ignorando mayúsculas/minúsculas
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}