package com.proyecto.tutorlink.service;
import com.proyecto.tutorlink.entity.Subject;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.repository.SubjectRepository;
import com.proyecto.tutorlink.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    //Agregar un nuevo subject
    @Transactional
    public Subject addSubject(Subject subject) {
        if (subjectRepository.existsByTitle(subject.getTitle())) {
            throw new IllegalStateException("A subject with the same title already exists");
        }
        return subjectRepository.save(subject);
    }
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public void deleteSubject(Long subjectId) throws CustomException {
        // Encontrar el Subject a eliminar
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalStateException("No Subject find by that Id" + subjectId));

        // Obtener todos los Teachers que tienen este Subject
        List<Teacher> teachers = teacherRepository.findBySubject(subject);

        // Establecer el Subject de estos Teachers a null o a otro Subject
        for (Teacher teacher : teachers) {
            teacher.setSubject(null);
            teacherRepository.save(teacher);
        }

        // Eliminar el Subject
        subjectRepository.delete(subject);
    }
}