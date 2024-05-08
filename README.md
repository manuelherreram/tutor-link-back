- BackEnd despliega en el puerto 8080
- la ruta para GET y POST es localhost:8080/api/teachers
- formato POSTMAN:
  {  
    "name": "Pedro Sanchez",  
    "dni": "987654321Y",  
    "description": "Profesor de Inglés",  
    "images": [  
        {  
            "url": "http://example.com/images/carlos1.jpg"  
        },  
        {  
            "url": "http://example.com/images/carlos2.jpg"  
        }  
    ]  
}  
- la base de datos temporal es H2 en MEM, se crea al levantar el back  url=jdbc:h2:mem:tutorlink
  consola: localhost:8080/H2-console  
  - user: sa  
  - password: sa
