package com.proyecto.tutorlink.service;
import com.proyecto.tutorlink.entity.Image;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

    @Service
    public class TeacherService {
        @Autowired
        private TeacherRepository teacherRepository;

        @Transactional


// ID se están asignando secuencialmente por bug de ID nulo


        public Teacher addTeacher(Teacher teacher) {
            if (teacherRepository.existsByDni(teacher.getDni())) {
                throw new IllegalStateException("A teacher with the same DNI already exists");
            }
            // cada imagen debe tener una referencia al teacher
            if (teacher.getImages() != null) {
                for (Image image : teacher.getImages()) {
                    image.setTeacher(teacher); // Establecer la relación
                }
            }
            return teacherRepository.save(teacher);
        }
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) throws CustomException {
        return teacherRepository.findById(id).orElseThrow(() -> new CustomException("Teacher not found"));
        }

    }


