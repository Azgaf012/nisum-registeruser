# Registro de Usuarios

Este proyecto permite el registro de usuarios a través de una API REST. Los usuarios pueden registrarse proporcionando sus detalles que incluyen nombre, correo electrónico, contraseña y una lista de teléfonos. Una vez registrado, el usuario recibe un token de acceso para interactuar con la API.

## Requisitos

- Java 17
- Gradle
- Docker
- Spring Boot 3

## Ejecución con Docker

- Construye la imagen de Docker, ubicate en la raiz del proyecto y ejecuta el siguiente comando para construir la imagen:
    ```bash
    docker build -t nombre-imagen .
    ```
- Ejecuta la imagen (verificar que el puerto 8080 esta disponible):
    ```bash
    docker run -p 8080:8080 nombre-imagen
    ```
    La aplicación estará disponible en http://localhost:8080.

## API Endpoint
### Registro de Usuario: POST /login/register

-   Estructura del payload para el registro
    ```json
    {
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.org",
        "password": "hunter2",
        "phones": [
            {
                "number": "1234567",
                "cityCode": "1",
                "countryCode": "57"
            }
        ]
    }
    ```
- Respuesta de éxito: 200 OK
    
  ```json
    {
        "id": "e1035043-d972-4e58-9701-c8576d620ff1",
        "created": "2023-10-02",
        "modified": "2023-10-02",
        "last_login": "2023-10-02",
        "token": "eyJhbGciOiJIUzI1NiJ9....",
        "isActive": true
    }
    ```
- Respuesta de error (Correo ya registrado o validación fallida): 400 Bad Request
    
  ```json
  {
        "mensaje": "El email ya está registrado."
  }
  ```
### Chequeo de Salud: GET /health

- Este endpoint está protegido y requiere el token de acceso proporcionado durante el registro.
- Respuesta: 403 Forbidden (sin token)
- Respuesta: 200 OK (con token válido)

  ```text
  Service is up and running!
  ```

### Validaciones

- La validación del correo electrónico y la contraseña se realiza contra las expresiones regulares configuradas en el archivo application.yml:

  ```yml
  validation:
      email-pattern: "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$"
      password-pattern: "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{7,}$"

  ```

### Autenticación y Token

- Al registrarse, los usuarios reciben un token JWT que les permite acceder a los endpoints protegidos de la API.
- El token debe ser incluido en el header Authorization de las solicitudes a endpoints protegidos.