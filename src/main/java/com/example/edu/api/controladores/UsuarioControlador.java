package com.example.edu.api.controladores;

import org.springframework.beans.factory.annotation.Autowired;
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

    // Inyección de la dependencia del servicio ClubServicio
    @Autowired
    private UsuarioServicio usuarioServicios;

  
    @PostMapping
    public String agregarUsuario(@RequestBody Usuarios usuario) {
        usuarioServicios.agregarUsuario(usuario);
        return "Usuario añadido con éxito, ID: " + usuario.getIdUser();
    }

    
    @PostMapping("/login")
    public String login(@RequestBody Usuarios usuario) {

    	return usuarioServicios.autenticarUsuario(usuario.getEmail(),usuario.getPasswordUser())
                .map(u 	-> "Autenticación exitosa del usuario: " + u.getNickUser())
                .orElse("Error de autenticación ");
    }
    
    @GetMapping("/helloUser")
    public String helloUser() {
        return "Hello, usuario!";	
    }


    
   
}