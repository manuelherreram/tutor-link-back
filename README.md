**ULTIMA VERSION BACK EN RAMA** rodrigo

- BackEnd despliega en el puerto 8080  
 

- **Rutas TEACHERS** 
  - Obtener todos los profesores:   
  localhost:8080/api/admin/teachers
  - Obtener 10 profesores al azar:  
  localhost:8080/api/public/index
  - Buscar por id:  
  localhost:8080/api/public/{id}
  - Guardar nuevo profesor:  
  localhost:8080/api/admin/teachers
  - Eliminar profesor:  
  localhost:8080/api/admin/teachers/{id}
  - Buscar profesores por categoría (ej. matemáticas):    
  - localhost:8080/api/public/teachers/category?subject=matematicas
- **Rutas USERS**  
 
  - Obtener todos los usuarios registrados:  
  localhost:8080/api/public/users  
    **POR AHORA SE MANTIENE EN RUTA PUBLICA**
  
**PENDIENTES:**
- Obtener Usuario por ID
- Eliminar Usuario por ID
 
- Formato:  

![image](https://github.com/manuelherreram/tutor-link-back/assets/97056237/1b5c1927-5256-4ef8-a9e3-f0b52c33459a)

- Base de datos temporal es H2 en MEM, se crea al levantar el back.
  url=jdbc:h2:mem:tutorlink  
  consola: localhost:8080/H2-console  
      user: sa  
      password: sa