package com.example.demo.Service;

import com.example.demo.Entity.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Crear un nuevo usuario
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Obtener un usuario por ID
    public Usuario findById(Integer id) {
        // .orElse(null) saca el Usuario del Optional, o devuelve null si no existe en MySQL
        return usuarioRepository.findById(id).orElse(null);
    }

    // Listar todos los usuarios
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Buscar por nombre (usa el uery Method que creamos en UsuarioRepository)
    public List<Usuario> findByNombre(String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Actualizar un usuario
    public Usuario update(Usuario usuario) {
        // Verificamos que exista antes de actualizar para no crear uno nuevo por accidente
        if (usuario.getId() != null && usuarioRepository.existsById(usuario.getId())) {
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    // Actualización parcial
    public Usuario patch(Integer id, Map<String, Object> updates) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombre":
                        usuario.setNombre((String) value);
                        break;
                    case "apellidos":
                        usuario.setApellidos((String) value);
                        break;
                    case "email":
                        usuario.setEmail((String) value);
                        break;
                    case "pasword":
                        usuario.setPasword((String) value);
                        break;
                }
            });
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    // Eliminar un usuario
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
}