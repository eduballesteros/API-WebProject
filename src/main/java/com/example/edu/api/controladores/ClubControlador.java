package com.example.edu.api.controladores; // Define el paquete en el que se encuentra la clase ClubControlador

import java.util.List; // Importa la clase List para trabajar con listas de objetos

import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para la inyección de dependencias
import org.springframework.web.bind.annotation.DeleteMapping; // Importa la anotación para manejar solicitudes DELETE
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación para manejar solicitudes GET
import org.springframework.web.bind.annotation.PathVariable; // Importa la anotación para extraer variables de la URL
import org.springframework.web.bind.annotation.PostMapping; // Importa la anotación para manejar solicitudes POST
import org.springframework.web.bind.annotation.RequestBody; // Importa la anotación para manejar el cuerpo de la solicitud
import org.springframework.web.bind.annotation.RequestMapping; // Importa la anotación para definir la ruta base
import org.springframework.web.bind.annotation.RestController; // Importa la anotación que indica que esta clase es un controlador REST

import com.example.edu.api.modelos.Club; // Importa la clase Club, que representa el modelo de datos de un club
import com.example.edu.api.servicios.ClubServicio; // Importa el servicio que maneja la lógica de negocio para clubes

// Anotación que define este controlador como un controlador REST
@RestController
@RequestMapping("/api/club") // Define la ruta base para las solicitudes relacionadas con clubes
public class ClubControlador {

    // Inyección de la dependencia del servicio ClubServicio
    @Autowired
    private ClubServicio clubServicio;

    // Maneja las solicitudes POST para la autenticación de un club
    @PostMapping("/login")
    public String login(@RequestBody Club club) {
        // Llama al método de autenticación en el servicio y devuelve un mensaje de éxito o error
        return clubServicio.autenticarClub(club.getNickClub(), club.getPasswordClub())
                .map(c -> "Autenticación exitosa para el club: " + c.getNickClub())
                .orElse("Error de autenticación");
    }

    // Maneja las solicitudes GET para listar todos los clubes
    @GetMapping
    public List<Club> listarClubes() {
        // Llama al servicio para obtener la lista de clubes
        return clubServicio.obtenerTodosLosClubes();
    }

    // Maneja las solicitudes POST para agregar un nuevo club
    @PostMapping
    public String agregarClub(@RequestBody Club club) {
        // Llama al servicio para agregar el club y devuelve un mensaje de éxito con el ID del club añadido
        clubServicio.agregarClub(club);
        return "Club añadido con éxito, ID: " + club.getIdClub(); // Devuelve el ID del nuevo club
    }

    // Maneja las solicitudes DELETE para borrar un club por su ID
    @DeleteMapping("/{id}")
    public String borrarClub(@PathVariable Long id) {
        // Llama al servicio para borrar el club con el ID especificado
        clubServicio.borrarClub(id);
        return "Club borrado con éxito"; // Devuelve un mensaje de éxito
    }
    
    @GetMapping("/helloClub")
    public String helloClub() {
        return "Hello, club!";
    }

    
   
}