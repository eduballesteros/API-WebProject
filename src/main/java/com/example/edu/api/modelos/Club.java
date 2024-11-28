package com.example.edu.api.modelos; // Define el paquete en el que se encuentra la clase Club

import com.fasterxml.jackson.annotation.JsonProperty; // Importa la anotación JsonProperty para la serialización/deserialización

import jakarta.persistence.Column;
import jakarta.persistence.Entity; // Importa la anotación Entity para indicar que esta clase es una entidad JPA
import jakarta.persistence.GeneratedValue; // Importa la anotación GeneratedValue para la generación automática de ID
import jakarta.persistence.GenerationType; // Importa la enumeración GenerationType para la estrategia de generación
import jakarta.persistence.Id; // Importa la anotación Id para marcar el campo ID
import jakarta.persistence.Table;

// Clase que representa un club en la base de datos
@Entity
@Table(name = "club")
public class Club {
    // Campo que representa el ID del club, se generará automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que el valor será generado por la base de datos
    @Column(name = "id_club")
    private Long idClub;  // Cambiado a Long y se generará automáticamente

    // Campo que representa el nombre del club, se utilizará para serialización/deserialización
    @Column(name = "nick_club", nullable = false, unique = true, length = 50)
    private String nickClub;

    // Campo que representa la contraseña del club, se utilizará para serialización/deserialización
    @Column(name = "password_club", nullable = false, length = 255)
    private String passwordClub;

    // Getters y Setters para acceder y modificar los atributos

    // Método que devuelve el ID del club
    public Long getIdClub() {
        return idClub;
    }

    // Método que establece el ID del club
    public void setIdClub(Long idClub) {
        this.idClub = idClub;
    }

    // Método que devuelve el nombre del club
    public String getNickClub() {
        return nickClub;
    }

    // Método que establece el nombre del club
    public void setNickClub(String nickClub) {
        this.nickClub = nickClub;
    }

    // Método que devuelve la contraseña del club
    public String getPasswordClub() {
        return passwordClub;
    }

    // Método que establece la contraseña del club
    public void setPasswordClub(String passwordClub) {
        this.passwordClub = passwordClub;
    }
}