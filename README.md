- BackEnd despliega en el puerto 8080
- Rutas: 
- Obtener todos los profesores: GET localhost:8080/api/teachers/admin
- Obtener 10 profesores al azar: GET localhost:8080/api/teachers/index
- Buscar por id: GET localhost:8080/api/teachers/{id}
- Guardar nuevo profesor: POST localhost:8080/api/teachers
- Eliminar profesor: DELETE localhost:8080/api/teachers/{id}
 
- Formato:  

![image](https://github.com/manuelherreram/tutor-link-back/assets/97056237/1b5c1927-5256-4ef8-a9e3-f0b52c33459a)


- la base de datos temporal es H2 en MEM, se crea al levantar el back.
  url=jdbc:h2:mem:tutorlink  
  consola: localhost:8080/H2-console  
      user: sa  
      password: sa
