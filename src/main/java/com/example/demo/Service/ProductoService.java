package com.example.demo;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        // Inicializamos algunos datos de ejemplo
        initSampleData();
    }

    private void initSampleData() {
        save(new Producto("Espejos par moto", 30000, "excelente", "Espejos modernos para motos deportivas o Urbanas"));
        save(new Producto("Espejos par moto", 30000, "excelente", "Espejos modernos para motos deportivas o Urbanas"));
        save(new Producto("Espejos par moto", 30000, "excelente", "Espejos modernos para motos deportivas o Urbanas"));
    }

    // Crear un nuevo producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // Obtener un usuario por ID
    public Producto findById(String id) {
        return productoRepository.findById(id);
    }

    // Listar todos los usuarios
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // Buscar por nombre
    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreContaining(nombre);
    }

    // Actualizar un usuario
    public Producto update(Producto producto) {return productoRepository.update(producto);
    }

    // Actualización parcial
    public Producto patch(String id, Map<String, Object> updates) {
        Producto producto = productoRepository.findById(id);
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
                        producto.setDescripcion((String)value);
                }
            });
            return productoRepository.update(producto);
        }
        return null;
    }

    // Eliminar un usuario
    public void deleteById(String id) {
        productoRepository.deleteById(id);
    }
}
