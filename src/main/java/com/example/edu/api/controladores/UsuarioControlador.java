package com.example.edu.api.controladores;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.edu.api.modelos.Usuarios;
import com.example.edu.api.servicios.UsuarioServicio;
	
// Anotación que define este controlador como un controlador REST
@RestController
@RequestMapping("/api/usuario") // Define la ruta base para las solicitudes relacionadas con clubes
public class UsuarioControlador {

	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioServicio.class);
	
 //Inyección de la dependencia del servicio ClubServicio
    @Autowired
    private UsuarioServicio usuarioServicios;

 //Para agregar un usuario nuevo
    @PostMapping
    public String agregarUsuario(@RequestBody Usuarios usuario) {
        usuarioServicios.agregarUsuario(usuario);
        return "Usuario añadido con éxito, ID: " + usuario.getIdUser();
    }

    
 //Método que maneja las solicitudes de login de los usuarios
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuarios usuario) {  

        // Llama al servicio solicitudDatos para verificar si el usuario existe en la base de datos
        return usuarioServicios.solicitudDatos(usuario.getNickUser(), usuario.getPasswordUser(), usuario.getEmail())
            
            // Si se encuentra el usuario, mapea el resultado a una respuesta exitosa con los datos del usuario
            .map(u -> {  
                
                // Respuesta exitosa (200 OK) con un cuerpo que contiene los datos del usuario
                // Utiliza Map.of para crear un mapa con los datos: nickname, email y contraseña
                return ResponseEntity.ok(Map.of( 
                    "nickUser", u.getNickUser(),  // Incluye el nickname del usuario
                    "email", u.getEmail(),        // Incluye el email del usuario
                    "passwordUser", u.getPasswordUser() // Incluye la contraseña del usuario
                ));
            })
            
            // Si no se encuentra el usuario, devuelve una respuesta con estado 401 (Unauthorized)
            // y un mensaje de error
            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("mensaje", "Error de autenticación")));     
    }


 //Metodo de prueba
    @GetMapping("/helloUser")
    public String helloUser() {
        return "Hello, usuario!";	
    }
    
 //Saca todo los usuarios de la base de datos  
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
    
    
 //Borra un usuario por id
    @DeleteMapping("/{id}")
    public String borrarUsuario(@PathVariable Long id) {
    	
        usuarioServicios.borrarUsuario(id);
        return "Usuario borrado con éxito"; // Devuelve un mensaje de éxito
    }
    
    
    
    
    @PutMapping("/modificar")
    public ResponseEntity<?> modificarUsuario(@RequestParam String name, @RequestParam String campo, @RequestParam String nuevoValor) {
        // Llamar al servicio para modificar el usuario
        Optional<Usuarios> usuario = usuarioServicios.modificarUsuario(name, campo, nuevoValor);

        // Si el usuario fue encontrado y modificado
        if (usuario.isPresent()) {
            return ResponseEntity.ok(Map.of(
                "mensaje", "Usuario modificado exitosamente",
                "usuario", usuario.get()
            ));
        } else {
            // Si no se encuentra el usuario o el campo no es válido
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(Map.of("mensaje", "Error: No se pudo modificar el usuario"));
        }
    }
}
    
    


    
   
