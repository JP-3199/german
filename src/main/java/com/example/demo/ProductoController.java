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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/producto")
@Tag(name = "Productos", description = "API para la gestión de productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Obtener todos los productos
    @Operation(summary = "Obtener todos los productos",
            description = "Devuelve una lista de todos los productos registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAll();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Buscar por nombre
    @Operation(summary = "Buscar productos por nombre",
            description = "Devuelve los productos cuyo nombre coincide con el texto buscado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Búsqueda realizada correctamente")
    })
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> getProductosByQuery(
            @RequestParam @Parameter(description = "Nombre del producto (parcial o completo)", required = true) String nombre) {
        List<Producto> productos = productoService.findByNombre(nombre);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Cabeceras
    @Operation(summary = "Obtener información del cliente",
            description = "Devuelve el User-Agent enviado en la cabecera de la petición.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Información del cliente obtenida")
    })
    @GetMapping("/cabecera")
    public ResponseEntity<String> getAgentInfo(
            @RequestHeader("User-Agent") @Parameter(description = "Cabecera User-Agent del cliente") String userAgent) {
        String info = "Información del cliente (User-Agent): " + userAgent;
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    // Obtener un producto por ID
    @Operation(summary = "Obtener un producto por ID",
            description = "Devuelve los datos de un producto según su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(
            @PathVariable @Parameter(description = "ID del producto", required = true) String id) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo producto
    @Operation(summary = "Crear un producto",
            description = "Registra un nuevo producto en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente")
    })
    @PostMapping
    public ResponseEntity<Producto> createProducto(
            @RequestBody @Parameter(description = "Datos del producto a crear", required = true) Producto producto) {
        Producto newProducto = productoService.save(producto);
        return new ResponseEntity<>(newProducto, HttpStatus.CREATED);
    }

    // Actualizar un producto existente (reemplazo completo)
    @Operation(summary = "Actualizar un producto",
            description = "Reemplaza todos los datos de un producto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(
            @PathVariable @Parameter(description = "ID del producto a actualizar", required = true) String id,
            @RequestBody @Parameter(description = "Nuevos datos completos del producto", required = true) Producto producto) {
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
    @Operation(summary = "Actualizar parcialmente un producto",
            description = "Modifica solo los campos enviados de un producto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado parcialmente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Producto> patchUpdateProducto(
            @PathVariable @Parameter(description = "ID del producto a modificar", required = true) String id,
            @RequestBody @Parameter(description = "Campos a actualizar (clave: valor)", required = true) Map<String, Object> updates) {
        Producto updatedProducto = productoService.patch(id, updates);
        if (updatedProducto != null) {
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un producto
    @Operation(summary = "Eliminar un producto",
            description = "Elimina un producto existente según su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(
            @PathVariable @Parameter(description = "ID del producto a eliminar", required = true) String id) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto != null) {
            productoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}