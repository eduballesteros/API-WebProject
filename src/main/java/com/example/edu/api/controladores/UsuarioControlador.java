package com.example.edu.api.controladores;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.edu.api.modelos.Usuarios;
import com.example.edu.api.servicios.UsuarioServicio;
	
// Anotación que define este controlador como un controlador REST
@RestController
@RequestMapping("/api/usuario") // Define la ruta base para las solicitudes relacionadas con clubes
public class UsuarioControlador {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioServicio.class);
	
    // Inyección de la dependencia del servicio ClubServicio
    @Autowired
    private UsuarioServicio usuarioServicios;

  
    @PostMapping
    public String agregarUsuario(@RequestBody Usuarios usuario) {
        usuarioServicios.agregarUsuario(usuario);
        return "Usuario añadido con éxito, ID: " + usuario.getIdUser();
    }

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuarios usuario) {
    	return usuarioServicios.solicitudDatos(usuario.getNickUser(), usuario.getPasswordUser(), usuario.getEmail())
    	        .map(u -> {
    	            return ResponseEntity.ok(Map.of(
    	                "nickUser", u.getNickUser(),
    	                "email", u.getEmail(),
    	                "passwordUser", u.getPasswordUser()
    	            ));
    	        })
    	        .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("mensaje", "Error de autenticación")));     
    }

    
    @GetMapping("/helloUser")
    public String helloUser() {
        return "Hello, usuario!";	
    }
    
    @GetMapping("/todos")
    public ResponseEntity<List<Usuarios>> obtenerTodosUsuarios() {
        // Registro del inicio de la solicitud
        logger.info("Solicitud recibida para obtener todos los usuarios.");
        
        // Llamada al servicio para obtener los usuarios
        List<Usuarios> usuarios = usuarioServicios.obtenerTodosUsuarios();

        // Registro de la cantidad de usuarios recuperados
        if (!usuarios.isEmpty()) {
            logger.info("Se encontraron {} usuarios en la base de datos.", usuarios.size());
            logger.debug("Usuarios recuperados: {}", usuarios);
        } else {
            logger.warn("No se encontraron usuarios en la base de datos.");
        }

        // Devolver la respuesta con los usuarios
        return ResponseEntity.ok(usuarios);
    }


    
   
}