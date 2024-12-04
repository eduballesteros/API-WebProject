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

### Verificar la Configuración de Maven
Asegúrate de que el proyecto contiene el archivo pom.xml en su raíz. Este archivo define todas las dependencias, el ciclo de vida del proyecto y otras configuraciones necesarias.

### Configurar la Base de Datos
Si estás utilizando una base de datos (como MySQL o PostgreSQL), asegúrate de que el archivo application.properties o application.yml esté correctamente configurado.

### Ejemplo de configuración para MySQL en application.properties:

properties
-  spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
-  spring.datasource.username=tu_usuario
-  spring.datasource.password=tu_contraseña
-  spring.jpa.hibernate.ddl-auto=update
-  spring.jpa.show-sql=true

### Construir el Proyecto
En la terminal, navega hasta el directorio raíz del proyecto y ejecuta el siguiente comando para construir el proyecto con Maven:
mvn clean install

Después de construir el proyecto, puedes ejecutar la aplicación de Spring Boot con Maven usando el siguiente comando:
- mvn spring-boot:run 
Esto iniciará el servidor y la aplicación estará disponible para su uso.

### Verificar la Ejecución
Una vez que la aplicación esté en ejecución, deberías ver un mensaje en la terminal indicando que el servidor está corriendo, normalmente en http://localhost:8080.

### Probar la API
Ahora puedes probar los endpoints de la API utilizando herramientas como Postman o cURL.

# Controladores
Esta sección describe los controladores disponibles en la API, sus métodos HTTP y los parámetros.

## Controlador de Usuarios
El controlador de Usuarios maneja todas las operaciones relacionadas con la entidad Usuarios. Permite crear, obtener, autenticar y borrar usuarios.

Endpoints
### 1. Crear Usuario
- **Método HTTP**: `POST`
- **Endpoint**: `/api/usuarios`
- **Descripción**: Crea un nuevo usuario.

#### Cuerpo de la solicitud:
```json
[
{
  "nickUser": "usuario1",
  "nameUser": "Usuario Uno",
  "passwordUser": "password123",
  "email": "usuario@dominio.com"
}
]
```
### 2. Obtener Todos los Usuarios
- **Método HTTP**: `GET`
- **Endpoint**: `/api/usuarios`
- **Descripción**: Obtiene la lista de todos los usuarios

### 3. Autenticar Usuario
- **Método HTTP**: `POST`
- **Endpoint**: `/api/usuarios/authenticate`
- **Descripción**: `Autentica a un usuario utilizando su nickname y contraseña.`


 #### Cuerpo de la solicitud:
```json

{
  "nickUser": "usuario1",
  "passwordUser": "password123"
}
```
### 4. Borrar Usuario
- **Método HTTP**: `DELETE`
- **Endpoint**: `/api/usuarios/{id}`
- **Descripción**: Elimina un usuario por su ID.
- **Parámetros**:
  `id (en la URL): ID del usuario a eliminar.`

## Controlador de Clubes
El controlador de Clubes maneja todas las operaciones relacionadas con la entidad Clubes. Permite crear, obtener, autenticar y eliminar clubes.


Endpoints
### 1. Crear Club
- **Método HTTP**: `POST`
- **Endpoint**: `/api/clubes`
- **Descripción**: Crea un nuevo club.

#### Cuerpo de la solicitud:
```json
[
  {
    "nickClub": "club1",
    "passwordClub": "password123"
  }
]
```
### 2. Obtener Todos los Clubes 
- **Método HTTP**: `GET`
- **Endpoint**: `/api/clubes`
- **Descripción**: Obtiene la lista de todos los clubes

### 3. Autenticar Club
- **Método HTTP**: `POST`
- **Endpoint**: `/api/clubes/authenticate`
- **Descripción**: `Autentica a un club utilizando su nickname y contraseña.`


 #### Cuerpo de la solicitud:
```json

{
   "nickClub": "club1",
  "passwordClub": "password123"
}
```
### 4. Borrar Club
- **Método HTTP**: `DELETE`
- **Endpoint**: `/api/clubes/{id}`
- **Descripción**: Elimina un club por su ID.
- **Parámetros**:
  `id (en la URL): ID del cluv a eliminar.`

# DTOs (Data Transfer Objects)

### 1. **UsuarioDTO**
Este DTO representa la estructura de datos de un **Usuario**.

#### Estructura:
```json
{
  "idUser": 1,
  "nickUser": "usuario1",
  "nameUser": "Usuario Uno",
  "passwordUser": "password123",
  "email": "usuario@dominio.com"
}
```
- idUser: ID único del usuario (generado automáticamente por la base de datos).
- nickUser: Nickname del usuario, utilizado para autenticación.
- nameUser: Nombre completo del usuario.
- passwordUser: Contraseña del usuario.
- email: Correo electrónico del usuario.

### 2. **ClubDTO**
Este DTO representa la estructura de datos de un **Club**.

#### Estructura:
```json
{
  "idClub": 1,
  "nickClub": "club1",
  "passwordClub": "password123"
}
```
- idClub: ID único del club (generado automáticamente por la base de datos).
- nickClub: Nickname del club, utilizado para autenticación.
- passwordClub: Contraseña del club.

# Modelos
### Campos y Tipos de Datos de los Modelos **Usuario** y **Club**

#### **Modelo Usuario**

| Campo          | Tipo de Dato       | Descripción                                                                 |
|----------------|--------------------|-----------------------------------------------------------------------------|
| `idUser`       | `Long`             | Identificador único del usuario (clave primaria). Se genera automáticamente. |
| `nickUser`     | `String` (50)      | Nombre de usuario único utilizado para la autenticación. (No puede ser nulo) |
| `nameUser`     | `String` (100)     | Nombre completo del usuario. (No puede ser nulo)                            |
| `passwordUser` | `String` (255)     | Contraseña del usuario. (No puede ser nulo)                                 |
| `dni`          | `String` (15)      | Número de identificación único del usuario (DNI). (No puede ser nulo)      |
| `email`        | `String` (100)     | Correo electrónico único del usuario. (No puede ser nulo)                   |
| `address`      | `String` (255)     | Dirección del usuario (opcional).                                           |
| `imgUser`      | `String` (2083)    | Ruta de la imagen del usuario (opcional).                                    |
| `admin`        | `Boolean`          | Indicador de si el usuario es administrador o no (por defecto `FALSE`).      |
| `idClub`       | `Long`             | Clave foránea que hace referencia al club al que pertenece el usuario.      |

#### **Modelo Club**

| Campo          | Tipo de Dato       | Descripción                                                                 |
|----------------|--------------------|-----------------------------------------------------------------------------|
| `idClub`       | `Long`             | Identificador único del club (clave primaria). Se genera automáticamente.   |
| `nickClub`     | `String` (50)      | Nombre único del club utilizado para la autenticación. (No puede ser nulo)  |
| `passwordClub` | `String` (255)     | Contraseña del club para autenticación. (No puede ser nulo)                 |


# Servicios de la API

### **Servicio de Usuarios**

El servicio de usuarios maneja toda la lógica relacionada con las operaciones CRUD de la entidad **Usuarios**. Estas operaciones incluyen la creación, actualización, obtención y eliminación de usuarios.

#### **Métodos Disponibles**

1. **Crear Usuario**
   - **Descripción**: Crea un nuevo usuario en la base de datos.
   - **Método**: `POST`
   - **Endpoint**: `/api/usuarios`
   - **Parámetros**:
     ```json
     {
       "nickUser": "usuario1",
       "nameUser": "Usuario Uno",
       "passwordUser": "password123",
       "email": "usuario@dominio.com"
     }
     ```
   - **Respuesta**:
     ```json
     {
       "idUser": 1,
       "nickUser": "usuario1",
       "nameUser": "Usuario Uno",
       "email": "usuario@dominio.com"
     }
     ```
   - **Descripción**: Este servicio crea un nuevo usuario con los datos proporcionados en la solicitud.

2. **Obtener Todos los Usuarios**
   - **Descripción**: Obtiene todos los usuarios registrados en la base de datos.
   - **Método**: `GET`
   - **Endpoint**: `/api/usuarios`
   - **Respuesta**:
     ```json
     [
       {
         "idUser": 1,
         "nickUser": "usuario1",
         "nameUser": "Usuario Uno",
         "email": "usuario@dominio.com"
       },
       {
         "idUser": 2,
         "nickUser": "usuario2",
         "nameUser": "Usuario Dos",
         "email": "usuario2@dominio.com"
       }
     ]
     ```
   - **Descripción**: Este servicio devuelve una lista de todos los usuarios registrados.

3. **Autenticar Usuario**
   - **Descripción**: Autentica un usuario utilizando su nickname y contraseña.
   - **Método**: `POST`
   - **Endpoint**: `/api/usuarios/authenticate`
   - **Parámetros**:
     ```json
     {
       "nickUser": "usuario1",
       "passwordUser": "password123"
     }
     ```
   - **Respuesta**:
     ```json
     {
       "status": "success",
       "message": "User authenticated successfully."
     }
     ```
   - **Descripción**: Este servicio autentica un usuario validando su nickname y contraseña.

4. **Eliminar Usuario**
   - **Descripción**: Elimina un usuario de la base de datos.
   - **Método**: `DELETE`
   - **Endpoint**: `/api/usuarios/{id}`
   - **Parámetros**:
     - `id` (en la URL): ID del usuario a eliminar.
   - **Respuesta**:
     ```json
     {
       "status": "success",
       "message": "User deleted successfully."
     }
     ```
   - **Descripción**: Este servicio elimina un usuario identificado por su ID.

---

### **Servicio de Clubes**

El servicio de clubes maneja toda la lógica relacionada con las operaciones CRUD de la entidad **Clubes**. Estas operaciones incluyen la creación, obtención, autenticación y eliminación de clubes.

#### **Métodos Disponibles**

1. **Crear Club**
   - **Descripción**: Crea un nuevo club en la base de datos.
   - **Método**: `POST`
   - **Endpoint**: `/api/clubes`
   - **Parámetros**:
     ```json
     {
       "nickClub": "club1",
       "passwordClub": "password123"
     }
     ```
   - **Respuesta**:
     ```json
     {
       "idClub": 1,
       "nickClub": "club1"
     }
     ```
   - **Descripción**: Este servicio crea un nuevo club con los datos proporcionados.

2. **Obtener Todos los Clubes**
   - **Descripción**: Obtiene todos los clubes registrados en la base de datos.
   - **Método**: `GET`
   - **Endpoint**: `/api/clubes`
   - **Respuesta**:
     ```json
     [
       {
         "idClub": 1,
         "nickClub": "club1"
       },
       {
         "idClub": 2,
         "nickClub": "club2"
       }
     ]
     ```
   - **Descripción**: Este servicio devuelve una lista de todos los clubes registrados.

3. **Autenticar Club**
   - **Descripción**: Autentica un club utilizando su nickname y contraseña.
   - **Método**: `POST`
   - **Endpoint**: `/api/clubes/authenticate`
   - **Parámetros**:
     ```json
     {
       "nickClub": "club1",
       "passwordClub": "password123"
     }
     ```
   - **Respuesta**:
     ```json
     {
       "status": "success",
       "message": "Club authenticated successfully."
     }
     ```
   - **Descripción**: Este servicio autentica un club validando su nickname y contraseña.

4. **Eliminar Club**
   - **Descripción**: Elimina un club de la base de datos.
   - **Método**: `DELETE`
   - **Endpoint**: `/api/clubes/{id}`
   - **Parámetros**:
     - `id` (en la URL): ID del club a eliminar.
   - **Respuesta**:
     ```json
     {
       "status": "success",
       "message": "Club deleted successfully."
     }
     ```
   - **Descripción**: Este servicio elimina un club por su ID.

  

