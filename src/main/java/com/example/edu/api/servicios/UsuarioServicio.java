package com.example.edu.api.servicios;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edu.api.modelos.Usuarios;
import com.example.edu.api.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioServicio.class);
	
	 @Autowired // Inyección de dependencias para el repositorio de usuarios
	    private UsuarioRepositorio usuarioRepositorio;

	    
	    // Método para agregar un nuevo usuario a la base de datos
	    public void agregarUsuario(Usuarios usuario) {
	        usuarioRepositorio.save(usuario);  // Guarda el usuario utilizando el repositorio
	    }
	    
	 // Obtener todos los usuarios
	    public List<Usuarios> obtenerTodosUsuarios() {
	        return usuarioRepositorio.findAll();
	    }

	    
	    public Optional<Usuarios> solicitudDatos(String nickUser, String passwordUser, String email) {
	        logger.info("Iniciando autenticación. NickUser: {}, Password: {}, Email{}", nickUser, passwordUser, email);

	        List<Usuarios> usuarios = usuarioRepositorio.findAll();

	        return usuarios.stream()
	                .peek(usuarioBD -> logger.debug("Comparando con usuario: {}", usuarioBD))
	                .filter(usuarioBD -> 
	                (usuarioBD.getNickUser().equals(nickUser) || usuarioBD.getEmail().equals(email)) &&
	                usuarioBD.getPasswordUser().equals(passwordUser)
	            )
	                .findFirst();
	    }
}
