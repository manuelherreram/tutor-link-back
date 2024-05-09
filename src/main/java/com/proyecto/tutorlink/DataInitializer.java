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
        teachers.add(new Teacher("Profesor Pedro 1", "DNI100001", "Descripción detallada del Profesor 1"));
        teachers.add(new Teacher("Profesor Ana 2", "DNI100002", "Descripción detallada del Profesor 2"));
        // Continuar añadiendo profesores según se requiera.

        teachers.forEach(teacher -> {
            Teacher savedTeacher = teacherRepository.save(teacher);
            imageRepository.save(new Image("http://example.com/images/profesor1-1.jpg", "Imagen 1 del Profesor Pedro 1", savedTeacher));
            imageRepository.save(new Image("http://example.com/images/profesor1-2.jpg", "Imagen 2 del Profesor Pedro 1", savedTeacher));
        });
    }
}
