# TemuFacebook

Una plataforma agenda enfocada en eventos que permite a los usuarios conectarse a través de intereses compartidos y actividades organizadas.

# Autores

**Jorge Andres Gonzalez Diaz**<br>
**William Alejandro Gonzalez Gomez**<br>
**Julian David Bocanegra Segura**<br>
**Diego Alejandro Gil Otálora**

Universidad Pedagógica y Tecnológica de Colombia  
Ingeniería de Sistemas y Computación - Ingeniería de Software I

Tunja, 2025

## Descripción

TemuFacebook es una aplicación web que combina las características de una red social tradicional con un sistema avanzado de gestión de eventos. Los usuarios pueden crear perfiles, conectarse con amigos, organizar eventos basados en sus hobbies e intereses, y participar en actividades sociales de manera organizada.

## Funcionalidades

### Gestión de Usuarios

- Creación y administración de perfiles personales
- Sistema de amistades y conexiones sociales
- Gestión de información de contacto y datos personales

### Sistema de Eventos

- Creación y gestión de eventos sociales
- Estados de eventos: Pendiente, Confirmado, En Curso, Completado, Cancelado
- Asignación de ubicaciones específicas con direcciones
- Listas de participantes y organizadores
- Eventos asociados y relacionados

### Hobbies e Intereses

- Catálogo de hobbies y actividades
- Asociación de eventos con intereses específicos
- Búsqueda de usuarios basada en intereses similares
- Recomendaciones basadas en preferencias

### Relaciones Sociales

- Red de amistades
- Participación en eventos
- Conexiones basadas en intereses comunes

## Stack Tecnológico

### Backend

- **Java 21** - Lenguaje de programación principal
- **Spring Boot 3.5.5** - Framework de desarrollo
- **Spring Data JPA** - Persistencia de datos
- **Spring Data Neo4j** - Integración con base de datos de grafos
- **Spring Data MongoDB** - Integración con base de datos de documentos
- **Thymeleaf** - Motor de plantillas

### Bases de Datos

- **PostgreSQL 15** - Datos estructurados de usuarios
- **Neo4j 5** - Relaciones sociales y conexiones
- **MongoDB 6** - Datos no estructurados y contenido flexible

## Instalación y Configuración

### Prerrequisitos

- Java 21 o superior
- Maven 3.8+
- Docker y Docker Compose

### Pasos de Instalación

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/tu-usuario/temufacebook.git
   cd temufacebook
   ```

2. **Iniciar servicios de base de datos**

   ```bash
   docker-compose up -d
   ```

3. **Compilar y ejecutar la aplicación**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Acceder a la aplicación**
   - Aplicación web: http://localhost:8080
   - Neo4j Browser: http://localhost:7474
   - PostgreSQL: localhost:5410
   - MongoDB: localhost:27017

### Configuración de Base de Datos

#### PostgreSQL

- Usuario: administrador
- Contraseña: postgresPass
- Base de datos: temufacebook_db
- Puerto: 5410

#### Neo4j

- Usuario: neo4j
- Contraseña: neo4jPass
- Puerto Web: 7474
- Puerto Bolt: 7687

#### MongoDB

- Usuario: administrador
- Contraseña: mongoPass
- Puerto: 27017

## Uso de la API

### Endpoints Principales

#### Usuarios

- GET /persons - Obtener todos los usuarios
- POST /persons - Crear nuevo usuario
- GET /persons/{id} - Obtener usuario por ID
- GET /persons/{id}/friends - Obtener amigos del usuario

#### Eventos

- GET /events - Obtener todos los eventos
- POST /events - Crear nuevo evento
- GET /events/{id} - Obtener evento por ID
- PUT /events/{id} - Actualizar evento
- DELETE /events/{id} - Eliminar evento

#### Hobbies

- GET /hobbies - Obtener todos los hobbies
- POST /hobbies - Crear nuevo hobby
- GET /hobbies/{id} - Obtener hobby por ID
