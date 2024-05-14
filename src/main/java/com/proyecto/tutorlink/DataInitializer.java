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

        teachers.add(new Teacher("Marcelo Díaz", "100001", "Experto en métodos educativos innovadores y tecnologías de aprendizaje.", subjectH));
        teachers.add(new Teacher("Lucía Fernández", "100002", "Amplia experiencia en educación primaria y desarrollo infantil.", subjectG));
        teachers.add(new Teacher("Carlos Ramírez", "100003", "Especialista en historia mundial y dinámicas de grupo.", subjectH));
        teachers.add(new Teacher("Sofía Morales", "100004", "Dedicada a la enseñanza del arte y la expresión creativa.", subjectG));
        teachers.add(new Teacher("Eduardo Vargas", "100005", "Profesor de matemáticas con enfoque en resolución de problemas.", subjectM));
        teachers.add(new Teacher("Laura Martínez", "100006", "Especialista en ciencias de la salud y educación para el bienestar.", subjectH));
        teachers.add(new Teacher("Antonio López", "100007", "Imparte clases de física avanzada y experimentación científica.", subjectM));
        teachers.add(new Teacher("Ana Sánchez", "100008", "Enfocada en técnicas de aprendizaje interactivo y educación inclusiva.", subjectM));
        teachers.add(new Teacher("Jorge Castillo", "100009", "Experto en literatura y análisis textual crítico.", subjectH));
        teachers.add(new Teacher("Marta Domínguez", "100010", "Profesora de química con pasión por la investigación y la ciencia.", subjectM));
        teachers.add(new Teacher("David Gómez", "100011", "Imparte conocimientos avanzados en geografía y estudios ambientales.", subjectH));
        teachers.add(new Teacher("Julia López", "100012", "Docente en lenguas extranjeras especializada en francés y español.", subjectM));
        teachers.add(new Teacher("Juan Ríos", "100013", "Profesor de música con énfasis en teoría musical y composición.", subjectG));
        teachers.add(new Teacher("María Pérez", "100014", "Veterana en pedagogía y estrategias de gestión educativa.", subjectH));
        teachers.add(new Teacher("José Ortiz", "100015", "Experto en economía y administración de empresas educativas.", subjectG));
        teachers.add(new Teacher("Laura Giménez", "100016", "Enseña habilidades de comunicación efectiva y oratoria.", subjectG));
        teachers.add(new Teacher("Carlos Ruiz", "100017", "Profesor de tecnología e informática aplicada a la educación.", subjectH));
        teachers.add(new Teacher("Ana Torres", "100018", "Especializada en educación especial y apoyo diferenciado.", subjectG));
        teachers.add(new Teacher("Luis Méndez", "100019", "Profesor de ciencias sociales con enfoque en sociología educativa.", subjectG));
        teachers.add(new Teacher("Marta Vega", "100020", "Imparte conocimientos en filosofía y pensamiento crítico.", subjectH));
        teachers.add(new Teacher("David Navarro", "100021", "Especialista en biología y ciencias naturales.", subjectG));
        teachers.add(new Teacher("Julia Casas", "100022", "Experta en psicología educativa y desarrollo del aprendizaje.", subjectM));
        teachers.add(new Teacher("Juan García", "100023", "Profesor de educación física y deportes.", subjectG));
        teachers.add(new Teacher("María Soto", "100024", "Enseña técnicas de estudio y preparación para exámenes.", subjectH));

        teachers.forEach(teacher -> {
            Teacher savedTeacher = teacherRepository.save(teacher);
            imageRepository.save(new Image("https://images.unsplash.com/photo-1546410531-bb4caa6b424d?q=80&w=1471&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Imagen 1 del Profesor", savedTeacher));
            imageRepository.save(new Image("https://images.unsplash.com/photo-1512238972088-8acb84db0771?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Imagen 2 del Profesor", savedTeacher));
            imageRepository.save(new Image("https://images.unsplash.com/flagged/photo-1550946107-8842ae9426db?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Imagen 3 del Profesor", savedTeacher));
            imageRepository.save(new Image("https://images.unsplash.com/photo-1597570889212-97f48e632dad?q=80&w=1476&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Imagen 4 del Profesor", savedTeacher));
            imageRepository.save(new Image("https://images.unsplash.com/photo-1555436169-20e93ea9a7ff?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Imagen 5 del Profesor", savedTeacher));
        });

    }
}

