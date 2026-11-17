package com.example.demo.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Config.DatabaseConfig;
import com.example.demo.Entity.Producto;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepository {

    // Guardar un producto (INSERT)
    public Producto save(Producto producto) {
        String sql = "INSERT INTO productos (id, nombre, precio, calificacion, descripcion) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getId());
            stmt.setString(2, producto.getNombre());
            stmt.setInt(3, producto.getPrecio());
            stmt.setString(4, producto.getCalificacion());
            stmt.setString(5, producto.getDescripcion());

            stmt.executeUpdate();
            return producto;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el producto en MySQL", e);
        }
    }

    // Encontrar producto por ID
    public Producto findById(String id) {
        String sql = "SELECT * FROM productos WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearProducto(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar producto por ID", e);
        }
        return null;
    }

    // Listar todos los productos
    public List<Producto> findAll() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar productos", e);
        }
        return productos;
    }

    // Filtrar por nombre
    public List<Producto> findByNombreContaining(String nombre) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE LOWER(nombre) LIKE ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nombre.toLowerCase() + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al filtrar productos por nombre", e);
        }
        return productos;
    }

    // Eliminar un producto
    public void deleteById(String id) {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar producto", e);
        }
    }

    // Actualizar un producto
    public Producto update(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, calificacion = ?, descripcion = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setString(3, producto.getCalificacion());
            stmt.setString(4, producto.getDescripcion());
            stmt.setString(5, producto.getId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0 ? producto : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar producto", e);
        }
    }

    // Método auxiliar para convertir una fila de MySQL en un objeto Producto
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getString("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getInt("precio"));
        producto.setCalificacion(rs.getString("calificacion"));
        producto.setDescripcion(rs.getString("descripcion"));
        return producto;
    }
}