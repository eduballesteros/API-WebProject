package com.example.edu.api.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edu.api.modelos.Usuarios;
import com.example.edu.api.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio {

	 @Autowired // Inyección de dependencias para el repositorio de usuarios
	    private UsuarioRepositorio usuarioRepositorio;

	    
	    // Método para agregar un nuevo usuario a la base de datos
	    public void agregarUsuario(Usuarios usuario) {
	        usuarioRepositorio.save(usuario);  // Guarda el usuario utilizando el repositorio
	    }

	    
	    public Optional<Usuarios> autenticarUsuario(String nickUser, String passwordUser) {
	        // Busca entre todos los clubes y filtra por nickname y contraseña
	        return usuarioRepositorio.findAll().stream()
	                .filter(usuario -> usuario.getNickUser().equals(nickUser) && usuario.getPasswordUser().equals(passwordUser))
	                .findFirst(); // Devuelve un Optional con el club encontrado, si existe
	    }
}
