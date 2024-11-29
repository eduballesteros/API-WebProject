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

  //Esta línea crea un logger utilizando la clase LoggerFactory.
  //El logger se usa para registrar eventos y mensajes de depuración.
	private static final Logger logger = LoggerFactory.getLogger(UsuarioServicio.class);
	
	 @Autowired // Inyección de dependencias para el repositorio de usuarios
	    private UsuarioRepositorio usuarioRepositorio;

	    
	 //Método para agregar un nuevo usuario a la base de datos
	    public void agregarUsuario(Usuarios usuario) {
	        usuarioRepositorio.save(usuario);  // Guarda el usuario utilizando el repositorio
	    }
	    
	    
	    
	 //Obtener todos los usuarios
	    public List<Usuarios> obtenerTodosUsuarios() {
	        return usuarioRepositorio.findAll();
	    }
	    
	    

	 //Obetener los datos de un usuario para un loggeo
	    public Optional<Usuarios> solicitudDatos(String nickUser, String passwordUser, String email) {
	      
	    //Mensaje log del inicio
	    	logger.info("Iniciando autenticación. NickUser: {}, Password: {}, Email{}", nickUser, passwordUser, email);

	    //Obtiene todos los usuarios desde el repositorio 
	        List<Usuarios> usuarios = usuarioRepositorio.findAll();

	     //Inicia un flujo de trabajo con la lista de usuarios
	        return usuarios.stream()
	        	//Registra en el log los detalles de cada usuario que está siendo comparado (nivel de detalle DEBUG)
	                .peek(usuarioBD -> logger.debug("Comparando con usuario: {}", usuarioBD))
	                
	            //Filtra los usuarios buscando que coincidan con el nickname o el email y que la contraseña también coincida
	                .filter(usuarioBD -> 
	                (usuarioBD.getNickUser().equals(nickUser) || usuarioBD.getEmail().equals(email)) &&
	                usuarioBD.getPasswordUser().equals(passwordUser)
	            )
	             // Devuelve el primer usuario que cumpla con los criterios de filtro o un Optional vacío si no se encuentra
	                .findFirst();
	    }
	    
	    
	    
	 // Método para borrar un usuario de la base de datos dado su ID
	    public void borrarUsuario(Long idUsuario) {
	        usuarioRepositorio.deleteById(idUsuario);  // Elimina el club utilizando su ID
	    }
	    
	    
	    
	    public Optional<Usuarios> modificarUsuario(String name, String campo, String nuevoValor) {
	        // Buscar al usuario por su name
	        Optional<Usuarios> usuarioBD = usuarioRepositorio.findAll().stream()
	                .peek(u -> logger.info("Comparando con usuario: {}", u.getNameUser()))
	                .peek(u -> logger.info("Nombre recibido por parámetro: {}", name)) 
	                .filter(u -> u.getNameUser().equals(name))
	                .findFirst();
	        
	        // Si se encuentra el usuario, se actualiza el campo
	        if (usuarioBD.isPresent()) {
	            Usuarios u = usuarioBD.get();
	            
	            switch (campo.toLowerCase()) {
		            case "nickuser":
	                    u.setNickUser(nuevoValor);
	                    break;
	                case "nameuser":
	                    u.setNameUser(nuevoValor);
	                    break;
	                case "passworduser":
	                    u.setPasswordUser(nuevoValor);
	                    break;
	                case "dni":
	                    u.setDni(nuevoValor);
	                    break;
	                case "email":
	                    u.setEmail(nuevoValor);
	                    break;
	                case "address":
	                    u.setAddress(nuevoValor);
	                    break;
	                case "imguser":
	                    u.setImgUser(nuevoValor);
	                    break;
	                
	                default:
	                    return Optional.empty(); // Si el campo no es válido, devolvemos un Optional vacío
	            }

	            // Guardar el usuario modificado
	            usuarioRepositorio.save(u);
	            return Optional.of(u);  // Devolver el usuario actualizado
	        }

	        return Optional.empty();  // Si no se encuentra el usuario, devolver un Optional vacío
	    }
	    
}
