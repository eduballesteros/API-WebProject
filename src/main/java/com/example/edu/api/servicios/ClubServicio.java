package com.example.edu.api.servicios; // Define el paquete en el que se encuentra la clase ClubServicio

import java.util.List; // Importa la clase List para manejar listas de objetos Club
import java.util.Optional; // Importa la clase Optional para manejar valores que pueden estar presentes o ausentes

import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired para la inyección de dependencias
import org.springframework.stereotype.Service; // Importa la anotación Service para definir esta clase como un servicio

import com.example.edu.api.modelos.Club; // Importa la clase Club, que es el modelo de la entidad
import com.example.edu.api.repositorios.ClubRepositorio; // Importa la interfaz ClubRepositorio para acceder a los datos

// Clase de servicio que contiene la lógica de negocio relacionada con los clubes
@Service // Indica que esta clase es un servicio y será gestionada por el contenedor de Spring
public class ClubServicio {

    @Autowired // Inyección de dependencias para el repositorio de clubes
    private ClubRepositorio clubRepositorio;

    // Método para autenticar un club utilizando su nickname y contraseña
    public Optional<Club> autenticarClub(String nickClub, String passwordClub) {
        // Busca entre todos los clubes y filtra por nickname y contraseña
        return clubRepositorio.findAll().stream()
                .filter(club -> club.getNickClub().equals(nickClub) && club.getPasswordClub().equals(passwordClub))
                .findFirst(); // Devuelve un Optional con el club encontrado, si existe
    }

    // Método para obtener todos los clubes almacenados en la base de datos
    public List<Club> obtenerTodosLosClubes() {
        return clubRepositorio.findAll(); // Llama al método findAll del repositorio para obtener la lista de clubes
    }

    // Método para agregar un nuevo club a la base de datos
    public void agregarClub(Club club) {
        clubRepositorio.save(club);  // Guarda el club utilizando el repositorio
    }

    // Método para borrar un club de la base de datos dado su ID
    public void borrarClub(Long idClub) {
        clubRepositorio.deleteById(idClub);  // Elimina el club utilizando su ID
    }
}
