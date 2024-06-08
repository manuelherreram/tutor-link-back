# Tutor Link BackEnd

## Descripción
Tutor Link es una plataforma diseñada para conectar estudiantes con profesores, permitiendo a los usuarios reservar clases de manera eficiente y gestionar horarios de manera efectiva. Esta aplicación ayuda a optimizar la asignación de recursos y mejora la experiencia educativa tanto para estudiantes como para profesores.

## Características
- **Reservas de Clases**: Los usuarios pueden buscar y reservar clases con profesores disponibles, basándose en la disponibilidad real y la gestión de conflictos de horarios.
- **Autenticación y Autorización**: Integración con Firebase para manejo seguro de sesiones de usuario y roles.
- **Gestión de Favoritos**: Los usuarios pueden marcar a sus profesores favoritos, optimizando la búsqueda y reserva de futuras sesiones.
- **Evaluación y Feedback**: Sistema para que los usuarios evalúen y comenten sobre las clases, mejorando la calidad del servicio educativo.

## Tecnologías Utilizadas
- Spring Boot
- Hibernate ORM con JPA
- Base de datos H2 en memoria
- Firebase para autenticación
- Swagger para documentación de la API

## Configuración Inicial
1. **Clonar el repositorio**: `git clone URL`
2. **Instalar dependencias**: `mvn install`
3. **Configurar la base de datos H2**:
  - URL: `jdbc:h2:mem:tutorlink`
  - Consola: [H2 Console](http://localhost:8080/H2-console)
  - Usuario: `sa`
  - Contraseña: `sa`
4. **Ejecutar la aplicación**: `mvn spring-boot:run`

## Uso
La API está documentada con Swagger, accesible desde `http://localhost:8080/swagger-ui.html` después de ejecutar la aplicación. Para acceder a Swagger:
- **Usuario**: user
- **Contraseña**: admin  

- ## Licencia
Distribuido bajo la licencia MIT. Ver `LICENSE` para más información.
