package com.example.demo;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private final ProductoService productoService;
    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAll();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    // Buscar por nombre

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> getProductosByQuery(@RequestParam String nombre) {
        List<Producto> productos = productoService.findByNombre(nombre);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    // Cabeceras
    @GetMapping("/cabecera")
    public ResponseEntity<String> getAgentInfo(@RequestHeader("User-Agent") String
                                                       userAgent) {
        String info = "Información del cliente (User-Agent): " + userAgent;
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable String id) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto newProducto = productoService.save(producto);
        return new ResponseEntity<>(newProducto, HttpStatus.CREATED);
    }
    // Actualizar un producto existente (reemplazo completo)
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable String id,
                                                 @RequestBody Producto producto) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto != null) {
            producto.setId(id);
            Producto updatedProducto = productoService.update(producto);
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Actualizar solo una parte de los datos
    @PatchMapping("/{id}")
    public ResponseEntity<Producto> patchUpdateProducto(@PathVariable String id,
                                                      @RequestBody Map<String, Object> updates) {
        Producto updatedProducto = productoService.patch(id, updates);
        if (updatedProducto != null) {
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable String id) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto != null) {
            productoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}