# Proyecto Integrado: Aplicación de Contacto entre Ofertantes y Consumidores de Actividades

Este repositorio contiene tanto el **cliente** (aplicación móvil desarrollada en Kotlin con Jetpack Compose y Retrofit) como el **servidor** (API REST creada con Spring Boot en Java) de una aplicación cuyo propósito es conectar **ofertantes** y **consumidores** de actividades de ocio, entretenimiento, deporte, aventura y más.

## Descripción del Proyecto

La aplicación facilita la interacción entre personas que **ofrecen** actividades (como recorridos históricos, deportes de aventura, clases de pádel, etc.) y personas que desean **participar** en ellas. Además, los consumidores pueden publicar anuncios de búsqueda específicos para actividades concretas, ofreciendo una vía de conexión directa entre ambos perfiles.

### Funcionalidades Principales

- **Registro y autenticación** de usuarios, ya sea ofertantes o consumidores de actividades.
- **Creación y visualización de ofertas** por parte de los ofertantes de actividades, con detalles sobre el tipo de actividad, ubicación, requerimientos de transporte, y si es necesario, la disponibilidad de materiales especiales.
- **Publicación de anuncios de búsqueda** por parte de los consumidores de actividades, permitiéndoles solicitar actividades específicas.
- **Búsqueda y filtrado de actividades y anuncios** por tipo de actividad, ubicación, fecha, etc., para facilitar a los usuarios encontrar lo que necesitan.
- **Sistema de comunicación** para que ofertantes y consumidores puedan **contactarse** directamente desde la aplicación.

### Ejemplos de Actividades

Algunos ejemplos de actividades que se pueden publicar en la aplicación incluyen:

- Recorridos guiados históricos, como una ruta por la Sevilla medieval.
- Actividades deportivas de aventura: barranquismo, escalada, rutas de senderismo.
- Clases deportivas, como clases de pádel o tenis.
- Juegos de rol en vivo y otras actividades de entretenimiento.

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
