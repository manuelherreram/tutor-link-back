package com.proyecto.tutorlink.service;
import com.proyecto.tutorlink.dto.TeacherDto;
import com.proyecto.tutorlink.entity.Characteristic;
import com.proyecto.tutorlink.entity.Image;
import com.proyecto.tutorlink.entity.Subject;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.entity.Availability;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.repository.*;
import com.proyecto.tutorlink.specification.TeacherSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

@Autowired
private AvailabilityRepository availabilityRepository;


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
        // Asociar el Subject existente o crear uno nuevo
        Subject subject = teacher.getSubject();
        if (subject != null) {
            Subject existingSubject = subjectRepository.findByTitle(subject.getTitle())
                    .orElse(subjectRepository.save(new Subject(subject.getTitle())));
            teacher.setSubject(existingSubject); // Asegura que el profesor use el subject existente o el recién creado
        } else {
            throw new IllegalStateException("Subject must be provided");
        }
        //asociar characteristic

        List<Characteristic> characteristic = teacher.getCharacteristics();
        /*List<Characteristic> characteristicAsignados= new ArrayList<>();
        if (characteristic != null)
        {
        for (Characteristic characteristics : characteristic) {
            Characteristic existingCharacteristic = characteristicRepository
                    .findById(characteristics.getId())
                    .orElse(characteristics);
            characteristicAsignados.add(existingCharacteristic);
            teacher.setCharacteristics(characteristicAsignados);
        }
        } else {throw new IllegalStateException("Characteristic must be provided");}*/
        if (characteristic != null)
        {
            teacher.setCharacteristics(characteristic);
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
        return randomTeachers.subList(0, count); // Devuelve los primeros 10
    }
    public Teacher getTeacherById(Long id) throws CustomException {
        return teacherRepository.findById(id).orElseThrow(() -> new CustomException("Teacher not found"));
    }
    public List<Teacher> getTeachersBySubjects(List<String> subjectTitles) {
        return teacherRepository.findBySubjectTitleIn(subjectTitles);
    }

    public void deleteTeacherById(Long id) throws CustomException {
        if (!teacherRepository.existsById(id)) {
            throw new CustomException("Teacher not found");
        }
        teacherRepository.deleteById(id);
    }
    //actualizar un profesor (nombre, dni, descripción, subject)
    // NOTA: ESTE METODO NO ACTUALIZA LAS IMAGENES
    // SI SE REQUIERE SE PODRIA IMPLEMENTAR EN UN METODO PROPIO

    @Transactional
    public Teacher updateTeacher(Long teacherId, Teacher updatedTeacherData) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id " + teacherId));

        teacher.setName(updatedTeacherData.getName());
        teacher.setDni(updatedTeacherData.getDni());
        teacher.setDescription(updatedTeacherData.getDescription());

        // Manejo de Subject
        if (updatedTeacherData.getSubject() != null && updatedTeacherData.getSubject().getTitle() != null) {
            String subjectTitle = updatedTeacherData.getSubject().getTitle();
            Subject subject = subjectRepository.findByTitle(subjectTitle)
                    .orElseThrow(() -> new RuntimeException("Subject with title '" + subjectTitle + "' does not exist"));
            teacher.setSubject(subject);
        }

        return teacherRepository.save(teacher);

    }

    @Transactional
    public void createTeacherWithCharacteristics(Teacher teacher, List<Characteristic> characteristicIds) {


        for (Characteristic characteristicas : characteristicIds) {

            long id = characteristicas.getId();
            Optional<Characteristic> characteristic = characteristicRepository.findById(id);
            characteristic.ifPresent(teacher::addCharacteristic);
        }

        teacherRepository.save(teacher);
    }
    //obtener todos los profesores con sus favoritos
    public List<TeacherDto> getAllTeachersWithFavorites(Long userId) {
        return teacherRepository.findAll().stream()
                .map(teacher -> new TeacherDto(teacher, favoriteRepository.isFavorite(userId, teacher.getId())))
                .collect(Collectors.toList());
    }

    public List<Teacher> getTeachersByCharacteristicIds(List<Long> characteristicIds) {
        if (characteristicIds == null || characteristicIds.isEmpty()) {
            throw new IllegalArgumentException("Characteristic IDs cannot be null or empty");
        }
        return teacherRepository.findByCharacteristicIds(characteristicIds, characteristicIds.size());
    }


    // PRUEBAS FILTRADO MIXTO por subjects y characteristics

    public List<Teacher> getTeachersByFilter(List<String> subjectTitles, List<Long> characteristicIds) {
            Specification<Teacher> spec = TeacherSpecification.byFilter(subjectTitles, characteristicIds);
            return teacherRepository.findAll(spec);
        }

    public List<Teacher> getTeachersByCharacteristics(List<Long> characteristicIds) {
        return teacherRepository.findByCharacteristicIds(characteristicIds, characteristicIds.size());
    }
    public List<Teacher> getTeachersByKeyword(String keyword) {
        Specification<Teacher> spec = TeacherSpecification.hasKeyword(keyword);
        return teacherRepository.findAll(spec);
    }

    //****BUSQUEDA POR SUBJECT Y AVAILABILITY****

    public List<Teacher> getAvailableTeachers(LocalDate startDate, LocalDate endDate, String subjectTitle) {
        List<Teacher> teachers = teacherRepository.findBySubjectTitle(subjectTitle);
        return teachers.stream()
                .filter(teacher -> isTeacherAvailable(teacher, startDate, endDate))
                .collect(Collectors.toList());
    }

    private boolean isTeacherAvailable(Teacher teacher, LocalDate startDate, LocalDate endDate) {
        List<Availability> availabilities = availabilityRepository.findByTeacherIdAndDateBetween(teacher.getId(), startDate, endDate);
        return !availabilities.isEmpty();
    }
}