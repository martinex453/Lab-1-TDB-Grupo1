# Laboratorio 1 - TDB - Grupo 1
Este repositorio contiene todos los archivos relacionados con el Laboratorio N°1 del Laboratorio de Base de Datos Avanzadas desarrollado por el grupo 1.

**IMPORTANTE:** 
- Para poder conectarse correctamente con la base de datos y el backend, asegurate de lo siguientes:
    1. Configura el archivo application.properties en la carpeta resources del backend:
        * Actualiza el usuario y contraseña de PostgreSQL. (usuario: postgres, contraseña: password por defecto)
        * Ajusta el puerto de conexión de la base de datos (5433 por defecto).
        * Configura el puerto donde estará disponible la aplicación Spring Boot (8090 por defecto).
    
    2. En caso de modificar la dirección del servidor o el puerto en application.properties, actualiza también el archivo .env en el frontend para asegurar que ambos coincidan.

    3. Por defecto la aplicación se ejecuta en el puerto 5173.

- Ya que hay una funcionalidad que muestra los usuario con más querys, es por ello que se crearán 2 usuarios administradores, al primer administrador se asociarán todas las inserciones iniciales hechas en la carga de datos, y este no será considerado para el top. Por esto es importante que para la administración de la aplicación se haga uso del segundo usuario administrador creador por postman.

## Requisitos previos
Para ejecutar correctamente todo el proyecto, además de los archivos del repositorio se requieren las siguientes tecnologías:
* Postgres SQL versión 12.22.
* PgAdmin versión 4.
* IntelliJ IDEA.
* VUE versión 3.
* Postman.
* Visual Studio Code.
* Gradle 8.10.2.

## Instrucciones de uso
1. Clona el repositorio en tu máquina local usando el siguiente comando:
```sh
git clone https://github.com/martinex453/Lab-1-TDB-Grupo1.git
```

2. Configurar la base de datos
* Abre pgAdmin o utiliza la consola de PostgreSQL.
* Ejecuta los siguientes comandos en la consola:
```sh
psql -U postgres
```
Ingresa la contraseña del usuario postgres cuando se solicite.
* Carga el archivo de creación de la base de datos: 
```sh
\i C:/ruta/DBCreate.sql  
```
Esto creará la estructura de la base de datos necesaria para la aplicación.

3. Ejecutar el backend
* Abre la carpeta back-grupo1 en IntelliJ IDEA.
* Ejecuta la aplicación haciendo clic en la opción "Run".

4. Crear usuarios administradores: 
Utiliza Postman para crear un primer usuario administrador enviando una solicitud POST a:
```sh
http://localhost:8090/cliente/crear_cuenta
```
Con el siguiente cuerpo JSON:
```sh
{
    "nombre": "admin1",
    "direccion": "1234",
    "email": "admin1@usach.cl",
    "telefono": "12345678",
    "contrasena": "123",
    "rol": "admin"
}
```

Luego crea un segundo usuario administrador enviando una solicitud POST a:
```sh
http://localhost:8090/cliente/crear_cuenta
```
Con el siguiente cuerpo JSON:
```sh
{
    "nombre": "admin2",
    "direccion": "1234",
    "email": "admin2@usach.cl",
    "telefono": "12345678",
    "contrasena": "123",
    "rol": "admin"
}
```
El primer usuario administrador es para efectos de la carga de datos que se hará a continuación, y el segundo administrador es para efectivamente administrar la aplicación.

5. Cargar datos en la base de datos: 
Desde la consola de PostgreSQL, ejecuta los siguientes comandos:
```sh
psql -U postgres
```
Ingresa la contraseña del usuario postgres cuando se solicite.
* Carga los datos para la base de datos: 
```sh
\i C:/ruta/loadDB.sql  
```
* No podrás iniciar sesión con los datos de los clientes cargados mediante este archivo, ya que las contraseñas en el archivo SQL no están encriptadas.
* Estos datos son únicamente para tener información que se pueda mostrar en las vistas del sistema o realizar pruebas.

6. Configurar y ejecutar el frontend: 
Dentro de la carpeta front-grupo1, abre la consola y ejecuta los siguientes comandos para instalar las dependencias y levantar el frontend:
```sh
npm install
npm install axios
npm install vue-cookies --save
npm install jwt-decode
npm run dev
```

7. Uso de la aplicación
* Accede a la aplicación usando las credenciales del segundo usuario administrador creado en el paso 4.
* También puedes registrar nuevos usuarios clientes para interactuar con el sistema.