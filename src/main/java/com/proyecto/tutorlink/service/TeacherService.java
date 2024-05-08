package com.proyecto.tutorlink.service;
import com.proyecto.tutorlink.entity.Teacher;
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
    public Teacher addTeacher(Teacher teacher) {
     //   if (teacherRepository.existsByDni(teacher.getDni())) {
     //       throw new IllegalStateException("A teacher with the same DNI already exists");
     //   }
        return teacherRepository.save(teacher);
    }
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
}


