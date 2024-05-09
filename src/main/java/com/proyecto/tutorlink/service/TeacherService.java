package com.proyecto.tutorlink.service;
import com.proyecto.tutorlink.entity.Image;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

    @Service
    public class TeacherService {
        @Autowired
        private TeacherRepository teacherRepository;

        @Transactional

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

        public List<Teacher> getRandomTeachers() {
            List<Teacher> allTeachers = teacherRepository.findAll();
            return selectRandomTeachers(allTeachers, 10);
        }

        private List<Teacher> selectRandomTeachers(List<Teacher> teachers, int count) {
            if (count >= teachers.size()) {
                return teachers;
            }
            List<Teacher> randomTeachers = new ArrayList<>(teachers);
            Collections.shuffle(randomTeachers); // Mezcla aleatoriamente la lista de profesores
            return randomTeachers.subList(0, count); // Devuelve los primeros 10 profesores de la lista mezclada
        }
    public Teacher getTeacherById(Long id) throws CustomException {
        return teacherRepository.findById(id).orElseThrow(() -> new CustomException("Teacher not found"));
        }

    }


