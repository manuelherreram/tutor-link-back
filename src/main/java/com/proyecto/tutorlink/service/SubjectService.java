package com.proyecto.tutorlink.service;
import com.proyecto.tutorlink.entity.Subject;
import com.proyecto.tutorlink.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    //Agregar un nuevo subject
    @Transactional
    public Subject addSubject(Subject subject) {
        if (subjectRepository.existsByTitle(subject.getTitle())) {
            throw new IllegalStateException("A subject with the same title already exists");
        }
        return subjectRepository.save(subject);
    }

}