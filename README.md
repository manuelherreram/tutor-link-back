
- BackEnd despliega en el puerto 8080  
 
- **Rutas TEACHERS** 
  - Obtener todos los profesores ADMIN:   
  localhost:8080/api/admin/teachers  
   
  - Obtener 10 profesores al azar:  
  localhost:8080/api/public/index  
   
  - Buscar por id:  
  localhost:8080/api/public/{id}  
   
  - Guardar nuevo profesor ADMIN:  
  localhost:8080/api/admin/teachers  
   
  - Eliminar profesor ADMIN:  
  localhost:8080/api/admin/teachers/{id}  
   
  - Buscar profesores por categoría(s) (ej. Matemáticas, Historia):    
    localhost:8080/api/public/teachers/category?subjects=Matematicas,Historia  
   
     
  - **RUTAS USUARIOS**  
 
    - Obtener todos los usuarios registrados (ADMIN):  
    localhost:8080/api/admin/users  
   
    - Crear usuario nuevo, se crean con rol USER por defecto.
      localhost:8080/api/public/createuser  
      Body:  
       {  
       "email": "",  
       "password": "",  
       "firstName": "",  
       "lastName": "",  
       }
     
    - Login usuario:
    localhost:8080/api/public/login
      Body:  
      {  
      "email": "",  
      "password": ""  
      }  
    - Setear el rol de un usuario (ADMIN):
      localhost:8080/api/admin/set-role
      Body:
        { 
        "uid":"",
        "role":""
        }
      
- **OTRAS**
  - Lista las categorías (subjects) disponibles.:
    localhost:8080/api/admin/subjects/list  
  
 
  - Agregar un nuevo subject (categorias) , solo ADMIN:  
   
    localhost:8080/api/admin/subjects/add
 ![img_1.png](img_1.png) 
   
  - Editar el subject (categorias) de un profesor, solo ADMIN:  
  
    localhost:8080/api/admin/teachers/{id}  

   ![img.png](img.png) 

- Base de datos temporal es H2 en MEM, se crea al levantar el back.
  url=jdbc:h2:mem:tutorlink  
  consola: localhost:8080/H2-console  
      user: sa  
      password: sa