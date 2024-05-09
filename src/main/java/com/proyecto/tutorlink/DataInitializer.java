package com.proyecto.tutorlink;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.entity.Image;
import com.proyecto.tutorlink.repository.TeacherRepository;
import com.proyecto.tutorlink.repository.ImageRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (teacherRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("Profesor Juan 3", "DNI100003", "Descripción detallada del Profesor 3"));
        teachers.add(new Teacher("Profesor María 4", "DNI100004", "Descripción detallada del Profesor 4"));
        teachers.add(new Teacher("Profesor José 5", "DNI100005", "Descripción detallada del Profesor 5"));
        teachers.add(new Teacher("Profesor Laura 6", "DNI100006", "Descripción detallada del Profesor 6"));
        teachers.add(new Teacher("Profesor Carlos 7", "DNI100007", "Descripción detallada del Profesor 7"));
        teachers.add(new Teacher("Profesor Ana 8", "DNI100008", "Descripción detallada del Profesor 8"));
        teachers.add(new Teacher("Profesor Luis 9", "DNI100009", "Descripción detallada del Profesor 9"));
        teachers.add(new Teacher("Profesor Marta 10", "DNI100010", "Descripción detallada del Profesor 10"));
        teachers.add(new Teacher("Profesor David 11", "DNI100011", "Descripción detallada del Profesor 11"));
        teachers.add(new Teacher("Profesor Julia 12", "DNI100012", "Descripción detallada del Profesor 12"));

        teachers.add(new Teacher("Profesor Juan 13", "DNI100013", "Descripción detallada del Profesor 13"));
        teachers.add(new Teacher("Profesor María 14", "DNI100014", "Descripción detallada del Profesor 14"));
        teachers.add(new Teacher("Profesor José 15", "DNI100015", "Descripción detallada del Profesor 15"));
        teachers.add(new Teacher("Profesor Laura 16", "DNI100016", "Descripción detallada del Profesor 16"));
        teachers.add(new Teacher("Profesor Carlos 17", "DNI100017", "Descripción detallada del Profesor 17"));
        teachers.add(new Teacher("Profesor Ana 18", "DNI100018", "Descripción detallada del Profesor 18"));
        teachers.add(new Teacher("Profesor Luis 19", "DNI100019", "Descripción detallada del Profesor 19"));
        teachers.add(new Teacher("Profesor Marta 20", "DNI100020", "Descripción detallada del Profesor 20"));
        teachers.add(new Teacher("Profesor David 21", "DNI100021", "Descripción detallada del Profesor 21"));
        teachers.add(new Teacher("Profesor Julia 22", "DNI100022", "Descripción detallada del Profesor 22"));


        teachers.forEach(teacher -> {
            Teacher savedTeacher = teacherRepository.save(teacher);
            imageRepository.save(new Image("https://images.unsplash.com/photo-1511629091441-ee46146481b6?w=400&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZmVzb3J8ZW58MHx8MHx8fDA%3D", "Imagen 1 del Profesor Pedro 1", savedTeacher));
            imageRepository.save(new Image("https://images.unsplash.com/photo-1580894732444-8ecded7900cd?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Imagen 2 del Profesor Pedro 1", savedTeacher));
        });
    }
}
