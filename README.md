# Red Social de Motos - API RESTful

Este proyecto es una aplicación desarrollada con **Spring Boot** destinada a una red social de motos que permite gestionar usuarios y clubes a través de una API RESTful. La aplicación se enfoca en ofrecer funcionalidades básicas de autenticación, gestión de información de usuarios y clubes, y la interacción con una base de datos mediante **JPA** (Java Persistence API).

La API está estructurada de manera modular, separando la lógica de la base de datos (repositorios) y la lógica de negocio (servicios). El objetivo principal es realizar operaciones CRUD (crear, leer, actualizar, eliminar) sobre las entidades **Club** y **Usuarios**.

Las siguientes secciones detallan los componentes principales de la aplicación, incluyendo las clases que representan las entidades (Club y Usuarios), los repositorios que interactúan con la base de datos, y los servicios que contienen la lógica para manejar las operaciones.

---

## Pasos para Iniciar el Proyecto con Maven

### Requisitos Previos
Asegúrate de tener instalados los siguientes componentes:
- **Java 21** o una versión compatible.
- **Maven** (verifica si Maven está instalado ejecutando `mvn -v` en la terminal).

### Clonar el Repositorio
Si el proyecto está alojado en un repositorio remoto (como GitHub), puedes clonarlo utilizando Git:

git clone <github.com/eduballesteros/API-WebProject>

###Verificar la Configuración de Maven
Asegúrate de que el proyecto contiene el archivo pom.xml en su raíz. Este archivo define todas las dependencias, el ciclo de vida del proyecto y otras configuraciones necesarias.

###Configurar la Base de Datos
Si estás utilizando una base de datos (como MySQL o PostgreSQL), asegúrate de que el archivo application.properties o application.yml esté correctamente configurado.

###Ejemplo de configuración para MySQL en application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

###Construir el Proyecto
En la terminal, navega hasta el directorio raíz del proyecto y ejecuta el siguiente comando para construir el proyecto con Maven:
mvn clean install

Después de construir el proyecto, puedes ejecutar la aplicación de Spring Boot con Maven usando el siguiente comando:
mvn spring-boot:run
Esto iniciará el servidor y la aplicación estará disponible para su uso.

###Verificar la Ejecución
Una vez que la aplicación esté en ejecución, deberías ver un mensaje en la terminal indicando que el servidor está corriendo, normalmente en http://localhost:8080.

###Probar la API
Ahora puedes probar los endpoints de la API utilizando herramientas como Postman o cURL.
