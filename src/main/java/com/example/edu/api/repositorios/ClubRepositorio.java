package com.example.edu.api.repositorios; // Define el paquete en el que se encuentra la interfaz ClubRepositorio

import org.springframework.data.jpa.repository.JpaRepository; // Importa la interfaz JpaRepository de Spring Data JPA
import com.example.edu.api.modelos.Club; // Importa la clase Club, que es el modelo de la entidad

// Interfaz que proporciona métodos para interactuar con la tabla 'Club' en la base de datos
public interface ClubRepositorio extends JpaRepository<Club, Long> {
    // No se requiere implementación, ya que JpaRepository proporciona métodos CRUD listos para usar
    // JpaRepository<Club, Long> indica que la entidad es 'Club' y su tipo de ID es 'Long'
}
