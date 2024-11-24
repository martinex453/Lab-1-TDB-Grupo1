# Laboratorio 1 - TDB - Grupo 1
Este repositorio contiene todos los archivos relacionados con el Laboratorio N°1 del Laboratorio de Base de Datos Avanzadas desarrollado por el grupo 1.

**IMPORTANTE:** 
*Para poder conectarse correctamente a la Base de Datos, se deben colocar el username y password con el que se configuró postgresSQL, se deben ajustar los puertos de conexión con la base de datos (5433), y el puerto donde la aplicación Spring Boot esta disponible (8090), todo en el archivo aplication.properties dentro de la carpeta resources en Backend,
*En caso de cambios en los parámetros anteriores, en el frontend en el archivo .env hay que asegurarse que la dirección del servidor y el puerto del backend coincidan con lo puesto en el archivo aplication.properties

## Instrucciones de uso
1. Clonar el repositorio:
```sh
git clone https://github.com/martinex453/Lab-1-TDB-Grupo1.git
```

2. Abrir pgAdmin y la consola, primero ejecutar en la consola:
```sh
psql -U postgres
```
Aqui se solicitara la contraseña del usuario postgres.
Luego se ejecuta:
```sh
\i C:/mis_archivos/DBCreate.sql
```
Con esto ya se habrá creado la base de datos.

3. Utilizando IntelliJ IDEA, ejecutar la aplicación haciendo clic en la opción "Run".

4. Crear un usuario administrador por postman.

5. Dentro de la carpeta front-grupo1, abrir una consola y ejecutar los siguientes comandos en orden: 
```sh
npm install axios
npm install vue-cookies --sabe
npm install jwt-decode
npm run dev
```
Con los primeros 3 comandos se instalan las librerías necesarias y con el último comando se levanta el frontend.

6. Ahora se puede proceder a hacer uso de el cliente Admin creado por postman, o de crear un nuevo usuario cliente.