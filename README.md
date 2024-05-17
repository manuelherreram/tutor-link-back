ULTIMA VERSION BACK TRABAJADA EN RAMA:  rodrigo

- BackEnd despliega en el puerto 8080
- Rutas: 
- Obtener todos los profesores: GET localhost:8080/api/teachers/admin
- Obtener 10 profesores al azar: GET localhost:8080/api/teachers/index
- Buscar por id: GET localhost:8080/api/teachers/{id}
- Guardar nuevo profesor: POST localhost:8080/api/teachers
- Eliminar profesor: DELETE localhost:8080/api/teachers/{id}
- Buscar profesore por categoría: GET  localhost:8080/api/teachers/bycategory?subject=matematicas
- Obtener todos los usuarios registrados: GET localhost:8080/api/users/
- Registrar un Nuevo Usuario POST localhost:8080/api/users/register
- Obtener Usuario por ID GET localhost:8080/api/users/{id}
- Eliminar Usuario por ID DELETE localhost:8080/api/users/{id}
 
- Formato:  

![image](https://github.com/manuelherreram/tutor-link-back/assets/97056237/1b5c1927-5256-4ef8-a9e3-f0b52c33459a)

![image](https://github.com/manuelherreram/tutor-link-back/assets/97056237/71d74de2-4f8e-47f6-b08e-b2224a025d58)

- la base de datos temporal es H2 en MEM, se crea al levantar el back.
  url=jdbc:h2:mem:tutorlink  
  consola: localhost:8080/H2-console  
      user: sa  
      password: sa
