package com.proyecto.tutorlink;

import com.proyecto.tutorlink.entity.Subject;
import com.proyecto.tutorlink.repository.SubjectRepository;
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

    @Autowired
    private  SubjectRepository subjectRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (teacherRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        List<Teacher> teachers = new ArrayList<>();

        Subject subjectM = new Subject("Matematica");
        Subject subjectH = new Subject("Historia");
        Subject subjectG = new Subject("Geografia");

        subjectRepository.save(subjectM);
        subjectRepository.save(subjectH);
        subjectRepository.save(subjectG);

        teachers.add(new Teacher("Docente Juan 1", "DNI100001", "Descripción detallada del Docente 1", subjectH));
        teachers.add(new Teacher("Docente María 2", "DNI100002", "Descripción detallada del Docente 2", subjectG));
        teachers.add(new Teacher("Docente Juan 3", "DNI100003", "Descripción detallada del Docente 3", subjectH));
        teachers.add(new Teacher("Docente María 4", "DNI100004", "Descripción detallada del Docente 4", subjectG));
        teachers.add(new Teacher("Docente José 5", "DNI100005", "Descripción detallada del Docente 5", subjectM));
        teachers.add(new Teacher("Docente Laura 6", "DNI100006", "Descripción detallada del Docente 6", subjectH));
        teachers.add(new Teacher("Docente Carlos 7", "DNI100007", "Descripción detallada del Docente 7", subjectM));
        teachers.add(new Teacher("Docente Ana 8", "DNI100008", "Descripción detallada del Docente 8", subjectM));
        teachers.add(new Teacher("Docente Luis 9", "DNI100009", "Descripción detallada del Docente 9", subjectH));
        teachers.add(new Teacher("Docente Marta 10", "DNI100010", "Descripción detallada del Docente 10", subjectM));
        teachers.add(new Teacher("Docente David 11", "DNI100011", "Descripción detallada del Docente 11", subjectH));
        teachers.add(new Teacher("Docente Julia 12", "DNI100012", "Descripción detallada del Docente 12", subjectM));
        teachers.add(new Teacher("Docente Juan 13", "DNI100013", "Descripción detallada del Docente 13", subjectG));
        teachers.add(new Teacher("Docente María 14", "DNI100014", "Descripción detallada del Docente 14", subjectH));
        teachers.add(new Teacher("Docente José 15", "DNI100015", "Descripción detallada del Docente 15", subjectG));
        teachers.add(new Teacher("Docente Laura 16", "DNI100016", "Descripción detallada del Docente 16", subjectG));
        teachers.add(new Teacher("Docente Carlos 17", "DNI100017", "Descripción detallada del Docente 17", subjectH));
        teachers.add(new Teacher("Docente Ana 18", "DNI100018", "Descripción detallada del Docente 18", subjectG));
        teachers.add(new Teacher("Docente Luis 19", "DNI100019", "Descripción detallada del Docente 19", subjectG));
        teachers.add(new Teacher("Docente Marta 20", "DNI100020", "Descripción detallada del Docente 20", subjectH));
        teachers.add(new Teacher("Docente David 21", "DNI100021", "Descripción detallada del Docente 21", subjectG));
        teachers.add(new Teacher("Docente Julia 22", "DNI100022", "Descripción detallada del Docente 22", subjectM));


        teachers.forEach(teacher -> {
            Teacher savedTeacher = teacherRepository.save(teacher);
            imageRepository.save(new Image("https://plus.unsplash.com/premium_photo-1682888442432-a1bc427c0d91?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Imagen 1 del Profesor", savedTeacher));
            imageRepository.save(new Image("https://images.unsplash.com/photo-1512238972088-8acb84db0771?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Imagen 2 del Profesor", savedTeacher));
            imageRepository.save(new Image("https://images.unsplash.com/flagged/photo-1550946107-8842ae9426db?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Imagen 3 del Profesor", savedTeacher));
            imageRepository.save(new Image("https://images.unsplash.com/photo-1597570889212-97f48e632dad?q=80&w=1476&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Imagen 4 del Profesor", savedTeacher));
            imageRepository.save(new Image("https://images.unsplash.com/photo-1555436169-20e93ea9a7ff?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Imagen 5 del Profesor", savedTeacher));
        });

    }
}

