package com.example.demo.Service;

import com.example.demo.Entity.Producto;
import com.example.demo.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Crear un nuevo producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // Obtener un producto por ID
    public Producto findById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    // Listar todos los productos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // Buscar por nombre
    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Actualizar un producto
    public Producto update(Producto producto) {
        if (producto.getId() != null && productoRepository.existsById(producto.getId())) {
            return productoRepository.save(producto);
        }
        return null;
    }

    // Actualización parcial
    public Producto patch(Integer id, Map<String, Object> updates) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombre":
                        producto.setNombre((String) value);
                        break;
                    case "precio":
                        producto.setPrecio((Integer) value);
                        break;
                    case "calificacion":
                        producto.setCalificacion((String) value);
                        break;
                    case "descripcion":
                        producto.setDescripcion((String) value);
                        break;
                }
            });
            return productoRepository.save(producto);
        }
        return null;
    }

    // Eliminar un producto
    public void deleteById(Integer id) {
        productoRepository.deleteById(id);
    }
}