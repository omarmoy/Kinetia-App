# KinetiaApp: Aplicación de Contacto entre Ofertantes y Consumidores de Actividades

*[Contenido en español]*

Este repositorio contiene tanto el **cliente** (aplicación móvil desarrollada en Kotlin con Jetpack Compose y Retrofit) como el **servidor** (API REST creada con Spring Boot en Java) de una aplicación cuyo propósito es conectar **ofertantes** y **consumidores** de actividades de ocio, entretenimiento, deporte, aventura y más.

## Descripción del Proyecto

La aplicación facilita la interacción entre personas que **ofrecen** actividades (como recorridos históricos, deportes de aventura, clases de pádel, etc.) y personas que desean **participar** en ellas. Además, los consumidores pueden publicar anuncios de búsqueda específicos para actividades concretas, ofreciendo una vía de conexión directa entre ambos perfiles.

### Funcionalidades Principales

- **Registro y autenticación** de usuarios, ya sea ofertantes o consumidores de actividades.
- **Creación y visualización de ofertas** por parte de los ofertantes de actividades, con detalles sobre el tipo de actividad, ubicación, requerimientos de transporte, y si es necesario, la disponibilidad de materiales especiales.
- **Publicación de anuncios de búsqueda** por parte de los consumidores de actividades, permitiéndoles solicitar actividades específicas.
- **Búsqueda y filtrado de actividades y anuncios** por tipo de actividad, ubicación, fecha, etc., para facilitar a los usuarios encontrar lo que necesitan.
- **Sistema de comunicación** para que ofertantes y consumidores puedan **contactarse** directamente desde la aplicación.

### Estructura del Proyecto

El proyecto está dividido en dos partes principales:

#### 1. Aplicación Cliente

- **Lenguaje**: Kotlin
- **Framework de UI**: Jetpack Compose para una interfaz de usuario moderna y reactiva.
- **Comunicación con el servidor**: Retrofit para realizar peticiones HTTP a la API REST.
- **Funcionalidades principales**: Registro de usuarios, creación y consulta de actividades, anuncios de búsqueda, y comunicación entre usuarios.

#### 2. Servidor (API REST)

- **Lenguaje**: Java
- **Framework**: Spring Boot para la gestión del backend y la creación de una API REST robusta.
- **Base de datos**: MySQL para almacenar de manera persistente los datos de usuarios, actividades, anuncios y mensajes.
- **Endpoints REST**: 
  - **Autenticación**: Registro e inicio de sesión de usuarios.
  - **Gestión de actividades**: Creación, edición y eliminación de actividades ofrecidas.
  - **Gestión de anuncios**: Publicación y consulta de anuncios de búsqueda de actividades.
  - **Comunicación**: Endpoint para facilitar el contacto entre ofertantes y consumidores.

### Requisitos

- **Base de datos**: MySQL 5.7 o superior.
- **Servidor de aplicaciones**: Java 11 o superior para Spring Boot.
- **Cliente móvil**: Android 6.0 o superior para ejecutar la aplicación en dispositivos Android.

### Configuración e Instalación

#### 1. Clonar el repositorio:

   ```bash
   git clone https://github.com/omarmoy/Kinetia-App
   ```

#### 2. Configuración del Servidor:

- Configurar la conexión a la base de datos MySQL en application.properties.
- Ejecutar la aplicación Spring Boot con el siguiente comando:
  ```bash
  ./mvnw spring-boot:run
  ```

#### 3. Ejecución de la Aplicación Cliente:

- Abrir el proyecto en Android Studio.
- Configurar la URL del servidor en el archivo retrofitConstants.
- Ejecutar la aplicación en un emulador o dispositivo Android conectado.



# KinetiaApp: Contact Application between Activity Providers and Consumers

*[Content in English]*

This repository contains both the **client** (mobile application developed in Kotlin with Jetpack Compose and Retrofit) and the **server** (REST API created with Spring Boot in Java) of an application whose purpose is to connect **offerers** and **consumers** of leisure, entertainment, sport, adventure activities and more.

## Project Description

The application facilitates interaction between people who **offer** activities (such as historical tours, adventure sports, paddle tennis lessons, etc.) and people who want to **participate** in them. In addition, consumers can post specific want ads for specific activities, providing a direct connection between the two profiles.

### Main functionalities

- **Registration and authentication** of users, whether they are activity providers or consumers.
- **Creation and display of offers** by activity providers, with details of activity type, location, transport requirements, and, if necessary, the availability of special materials.
- **Posting of want ads** by activity consumers, allowing them to request specific activities.
- **Search and filtering of activities and ads** by activity type, location, date, etc., to make it easier for users to find what they need.
- **Communication system** so that providers and consumers can **contact each other** directly from the application.

### Project structure

The project is divided into two main parts:

#### 1. Client Application

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose for a modern and responsive user interface.
- **Server communication**: Retrofit for making HTTP requests to the REST API.
- **Main functionalities**: User registration, activity creation and query, search ads, and communication between users.

#### 2. Server (REST API)

- **Language**: Java
- **Framework**: Spring Boot for backend management and creation of a robust REST API.

### Configuration and Installation

#### 1. Clone the repository:

   ```bash
   git clone https://github.com/omarmoy/Kinetia-App
   ```

#### 2. Server configuration:

- Configure the MySQL database connection in application.properties.
- Run the Spring Boot application with the following command:
  ```bash
  ./mvnw spring-boot:run
  ```

#### 3. Running the Client Application:

- Open the project in Android Studio.
- Set the server URL in the retrofitConstants file.
- Run the application on an emulator or connected Android device.

