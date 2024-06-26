package com.proyecto.tutorlink;

import com.proyecto.tutorlink.entity.*;
import com.proyecto.tutorlink.enums.ReservationStatus;
import com.proyecto.tutorlink.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

//@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CharacteristicRepository characteristicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AvailabilityRepository availabilityRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRepository.count() == 0) {
            log.info("Initializing data...");
            initializeData();
        }

    }
@Transactional
    private void initializeData() {

        User user1 = new User("Juan Pablo", "Sopa", "juan.sopa@mail.com", "password123", "ROLE_USER", "1234567892", "Calle Lejos 666", "Stgo", "Chile", "4pATozFoE1c5IXrR4og80dxPkT32");
        User user3 = new User("Joe", "Pino", "joe.pino@mail.com", "password123", "ROLE_USER", "1234567892", "Calle Lejos 666", "Stgo", "Chile", "8IeihYcTxBb5J03vSFW5NxHZZTn2");

        userRepository.save(user1);

        userRepository.save(user3);


        Subject subjectM = new Subject("Matematicas");
        Subject subjectH = new Subject("Historia");
        Subject subjectG = new Subject("Geografia");

        subjectRepository.save(subjectM);
        subjectRepository.save(subjectH);
        subjectRepository.save(subjectG);

        Characteristic certificacion = new Characteristic("Licenciado en Educacion", "https://static.thenounproject.com/png/3849525-200.png");
        Characteristic idiomas = new Characteristic("Ingles", "https://static.thenounproject.com/png/2711986-200.png");
        Characteristic clasePrueba = new Characteristic("Clase de Prueba", "https://static.thenounproject.com/png/31658-200.png");
        Characteristic clasesPresenciales = new Characteristic("Clases Presenciales", "https://static.thenounproject.com/png/6407-200.png");
        Characteristic clasesGrupales = new Characteristic("Clases Grupales", "https://static.thenounproject.com/png/3370603-200.png");
        Characteristic superProfesor = new Characteristic("SuperProfe", "https://static.thenounproject.com/png/3124376-200.png");

        characteristicRepository.save(certificacion);
        characteristicRepository.save(idiomas);
        characteristicRepository.save(clasePrueba);
        characteristicRepository.save(clasesPresenciales);
        characteristicRepository.save(clasesGrupales);
        characteristicRepository.save(superProfesor);

        List<Characteristic> allCharacteristics = List.of(certificacion, idiomas, clasePrueba, clasesPresenciales, clasesGrupales, superProfesor);
        List<Characteristic> characteristicsForTeacher1 = new ArrayList<>(allCharacteristics);
        List<Characteristic> characteristicsForTeacher2 = List.of(clasesGrupales, idiomas);
        List<Characteristic> characteristicsForTeacher3 = List.of(superProfesor, clasesPresenciales);

        Teacher teacher1 = new Teacher("Marcelo Díaz", "100001", "Experto en métodos educativos innovadores y tecnologías de aprendizaje.", subjectH, characteristicsForTeacher1);
        Teacher savedTeacher1 = teacherRepository.save(teacher1);
        List<String> imagesTeacher1 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%201%2F10.png?alt=media&token=305b3b46-cee8-42d5-8001-5d02299cb4ea",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%201%2FPizarra%20ejercicio%20-%20copia.jpg?alt=media&token=c76e233f-90a7-4f93-a126-224db727f980",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%201%2FPizarra%20matem%C3%A1tica%20-%20copia.jpg?alt=media&token=ccc05c53-be6e-4c5f-a7ba-a9596cea0544",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%201%2Fclase%20online%20computador.jpg?alt=media&token=4a072f98-720b-4014-8a34-ca12d47db196",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%201%2Fclase%20online%20videollamada%20-%20copia.jpg?alt=media&token=19199490-daa3-4beb-865c-2f5b0b3e0a74"
        );
        imagesTeacher1.forEach(url -> imageRepository.save(new Image(url, "Imagen del Profesor Marcelo Díaz", savedTeacher1)));

        Teacher teacher2 = new Teacher("Lucía Fernández", "100002", "Amplia experiencia en educación primaria y desarrollo infantil.", subjectG, characteristicsForTeacher2);
        Teacher savedTeacher2 = teacherRepository.save(teacher2);
        List<String> imagesTeacher2 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2019%2FJ.png?alt=media&token=7b73b477-54a8-4b95-b744-406167eb9ee6",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2019%2Fni%C3%B1os%20primaria%20-%20copia.jpg?alt=media&token=96a7ec88-727e-4c77-9dcf-c3a2120dd49d",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2019%2FPizarra%20ejercicio%20-%20copia.jpg?alt=media&token=fb1d3fdf-b0d7-490d-a7ff-80d510373470",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2019%2Fclase%20online%20computador2%20-%20copia.jpg?alt=media&token=76e6e5cb-1d31-4e14-ba19-b4a10298b6f5",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2019%2FPizarra%20matem%C3%A1tica%20-%20copia.jpg?alt=media&token=1e4b559c-371c-485a-9aab-e74c0076bae8"
        );
        imagesTeacher2.forEach(url -> imageRepository.save(new Image(url, "Imagen de la Profesora Lucía Fernández", savedTeacher2)));

        Teacher teacher3 = new Teacher("Carlos Ramírez", "100003", "Especialista en historia mundial y dinámicas de grupo.", subjectH, characteristicsForTeacher3);
        Teacher savedTeacher3 = teacherRepository.save(teacher3);
        List<String> imagesTeacher3 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%202%2F20.png?alt=media&token=5b9bfa53-fb5b-4946-964d-e98f3a0d9d85",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%202%2Fni%C3%B1os%20ciencias2%20-%20copia.jpg?alt=media&token=520c9bd8-fa4d-4175-acee-851dff29420f",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%202%2Fclase%20online%20computador2%20-%20copia.jpg?alt=media&token=7a684671-75a3-4805-bd70-d85cbfff7925",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%202%2Fclase%20online%20videollamada%20-%20copia.jpg?alt=media&token=c5a9de54-6fd8-407a-bec6-9d2191e5dbb3",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%202%2FPizarra%20matem%C3%A1tica%20-%20copia.jpg?alt=media&token=fd99e02d-4c98-4e9e-ab6a-b1f9bcc2abbd"
        );
        imagesTeacher3.forEach(url -> imageRepository.save(new Image(url, "Imagen del Profesor Carlos Ramírez", savedTeacher3)));

        Teacher teacher4 = new Teacher("Sofía Venegas", "100004", "Dedicada a la enseñanza del arte y la expresión creativa.", subjectG, characteristicsForTeacher1);
        Teacher savedTeacher4 = teacherRepository.save(teacher4);
        List<String> imagesTeacher4 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%209%2F90.png?alt=media&token=f02f9d7c-6442-4f6f-989d-2a6bfb8a95e9",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%209%2F91.png?alt=media&token=262c33ea-41e6-4e69-8694-58b7d5a4f7d1",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%209%2FProfesora%20pizarra.jpg?alt=media&token=c2c2d016-3ea9-447b-b5e1-661633a6bc33",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%209%2FProfesora%20pizarra2.jpg?alt=media&token=acc77181-2b2c-4bf3-b997-bd17f483d75d",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%209%2FProfesoraPizarra2%20grupo.jpg?alt=media&token=ebf6cf1e-9475-4128-bb4c-12eb969b8a3e"
        );
        imagesTeacher4.forEach(url -> imageRepository.save(new Image(url, "Imagen de la Profesora Sofía Venegas", savedTeacher4)));

        Teacher teacher5 = new Teacher("Eduardo Vargas", "100005", "Profesor de matemáticas con enfoque en resolución de problemas.", subjectM, characteristicsForTeacher2);
        Teacher savedTeacher5 = teacherRepository.save(teacher5);
        List<String> imagesTeacher5 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%207%2F70.png?alt=media&token=3d46fee4-a74f-44c9-b340-b04f6f0ac070",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%207%2F71.png?alt=media&token=d07255d8-7990-4905-8a37-248936ae2559",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%207%2FPizarra%20matem%C3%A1tica%20-%20copia.jpg?alt=media&token=64c4d8eb-e004-45e5-b780-45c7bd0cd7c4",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%207%2Fclase%20online%20computador2%20-%20copia.jpg?alt=media&token=e065196e-1c45-463e-b24a-939165e205c7",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%207%2Fclase%20online%20videollamada%20-%20copia.jpg?alt=media&token=fa327452-634b-41bb-8c0c-2b7cc7942d00"
        );
        imagesTeacher5.forEach(url -> imageRepository.save(new Image(url, "Imagen del Profesor Eduardo Vargas", savedTeacher5)));

        Teacher teacher6 = new Teacher("Laura Martínez", "100006", "Especialista en ciencias de la salud y educación para el bienestar.", subjectH, characteristicsForTeacher3);
        Teacher savedTeacher6 = teacherRepository.save(teacher6);
        List<String> imagesTeacher6 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%208%2F80.png?alt=media&token=2bbf4482-64df-4716-bda5-8c54b84e22ca",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%208%2F81.png?alt=media&token=35769389-bfc5-4e7c-9ae9-945713790657",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%208%2Fclase%20online%20videollamada%20-%20copia.jpg?alt=media&token=d4126569-14f6-4a38-8530-8d1cb2ba0312",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%208%2Fni%C3%B1os%20levantando%20mano%20-%20copia.jpg?alt=media&token=2db3401a-c8e3-4584-a901-d507319129f5",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%208%2Fni%C3%B1os%20primaria%20-%20copia.jpg?alt=media&token=03b94bd8-9321-44f1-b869-a6c36c50133f"
        );
        imagesTeacher6.forEach(url -> imageRepository.save(new Image(url, "Imagen de la Profesora Laura Martínez", savedTeacher6)));

        Teacher teacher7 = new Teacher("Antonio López", "100007", "Imparte clases de física avanzada y yoga.", subjectM, characteristicsForTeacher1);
        Teacher savedTeacher7 = teacherRepository.save(teacher7);
        List<String> imagesTeacher7 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%206%2F60.png?alt=media&token=3822621d-a6b0-4971-bb29-f185afdf4a7a",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%206%2FProfesor%20Yoga.jpg?alt=media&token=38e951d8-08e5-4725-a4e7-2979c3c205ba",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%206%2Fyoga%20online.jpg?alt=media&token=17826b7e-5413-4f68-82b2-9c407b3fc217",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%206%2Fyoga.jpg?alt=media&token=c12aa3bc-7f16-42ca-9cd3-c0d36609c90d",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%206%2Fyoga2.jpg?alt=media&token=27c0cc24-f196-4e1f-a700-fea0c3c64dd2"
        );
        imagesTeacher7.forEach(url -> imageRepository.save(new Image(url, "Imagen del Profesor Antonio López", savedTeacher7)));

        Teacher teacher8 = new Teacher("Ana Sánchez", "100008", "Enfocada en técnicas de aprendizaje interactivo y educación inclusiva.", subjectM, characteristicsForTeacher2);
        Teacher savedTeacher8 = teacherRepository.save(teacher8);
        List<String> imagesTeacher8 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2020%2FK.png?alt=media&token=4b0ac5fa-93e9-4308-b7f5-f91cb4099283",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2020%2Fclase%20online%20videollamada%20-%20copia.jpg?alt=media&token=b1d1525e-ac35-4412-8a8f-5093b5711745",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2020%2Fk1.png?alt=media&token=c78882e0-9fcd-4255-bfbf-a8d93ddca13f",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2020%2Fni%C3%B1os%20ciencias2%20-%20copia.jpg?alt=media&token=af696cf6-af45-486c-aa85-2a27ce7a1f1e",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2020%2Fni%C3%B1os%20secundaria%20-%20copia.jpg?alt=media&token=bf32f777-b739-4705-962d-99efbc525bec"
        );
        imagesTeacher8.forEach(url -> imageRepository.save(new Image(url, "Imagen de la Profesora Ana Sánchez", savedTeacher8)));

        Teacher teacher9 = new Teacher("Jorge Castillo", "100009", "Experto en literatura y análisis textual crítico.", subjectH, characteristicsForTeacher3);
        Teacher savedTeacher9 = teacherRepository.save(teacher9);
        List<String> imagesTeacher9 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%205%2F50.png?alt=media&token=18d40143-5d46-45aa-acf8-753ea513cc66",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%205%2F51.png?alt=media&token=43f7e1a9-f679-466d-a8fc-e1d280e0d0fd",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%205%2F52.png?alt=media&token=e708302b-ce24-4cc5-a75a-1cf7e884e5ae",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%205%2FPizarra%20ejercicio%20-%20copia.jpg?alt=media&token=eb7c1f75-f61e-4b8f-bfed-199655783162",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%205%2FPizarra%20matem%C3%A1tica.jpg?alt=media&token=20eafa35-712e-4388-bb26-2e4ab94a63eb"
        );
        imagesTeacher9.forEach(url -> imageRepository.save(new Image(url, "Imagen del Profesor Jorge Castillo", savedTeacher9)));

        Teacher teacher10 = new Teacher("Jeon Ji Hyun", "100010", "Profesora de química con pasión por la investigación y la ciencia.", subjectM, characteristicsForTeacher1);
        Teacher savedTeacher10 = teacherRepository.save(teacher10);
        List<String> imagesTeacher10 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2018%2FI.png?alt=media&token=5bb1d8f5-dfda-4ee3-bc21-ff636a712764",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2018%2FProfesora%20asi%C3%A1tica.jpg?alt=media&token=eae5e421-f850-4acc-836d-a5aa6e8b23fd",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2018%2Fclase%20online%20videollamada%20-%20copia.jpg?alt=media&token=af560cfe-0383-4f23-98ba-6dd81177dc40",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2018%2Fni%C3%B1os%20ciencias%20-%20copia.jpg?alt=media&token=bf075944-13ec-4b45-b9f3-a8a38d404d39",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2018%2Fni%C3%B1os%20secundaria%20-%20copia.jpg?alt=media&token=f76cc90e-8f08-4f80-88f7-7e2a1ef4241a"
        );
        imagesTeacher10.forEach(url -> imageRepository.save(new Image(url, "Imagen de la Profesora Jeon Ji Hyun", savedTeacher10)));

        Teacher teacher11 = new Teacher("David Gómez", "100011", "Imparte conocimientos avanzados en geografía y estudios ambientales.", subjectH, characteristicsForTeacher2);
        Teacher savedTeacher11 = teacherRepository.save(teacher11);
        List<String> imagesTeacher11 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2015%2FF.jpg?alt=media&token=dc847730-7da6-4f21-b7fe-dd2f21a02d51",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2015%2FPizarra%20matem%C3%A1tica.jpg?alt=media&token=cf2f29ba-8e1a-4f1c-a932-4d9b4f33c3ec",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2015%2Fclase%20online%20videollamada.jpg?alt=media&token=1bd6530d-a8ca-491e-b25c-eb2303d4031f",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2015%2Fni%C3%B1os%20ciencias.jpg?alt=media&token=fea01ccd-f13c-4a8f-84be-3bac958a5920",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2015%2Fni%C3%B1os%20ciencias2.jpg?alt=media&token=33ab9da2-2a8d-41a0-aade-babfb819aceb"
        );
        imagesTeacher11.forEach(url -> imageRepository.save(new Image(url, "Imagen del Profesor David Gómez", savedTeacher11)));

        Teacher teacher12 = new Teacher("Julia Cotton", "100012", "Docente en lenguas extranjeras especializada en francés y español.", subjectM, characteristicsForTeacher3);
        Teacher savedTeacher12 = teacherRepository.save(teacher12);
        List<String> imagesTeacher12 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2017%2FH.png?alt=media&token=999c60ed-2924-41be-a729-27ba072875aa",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2017%2FProfesora%2017.jpg?alt=media&token=39db95a5-2509-4ff4-be85-80cd2ab314b7",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2017%2Fclase%20online%20videollamada%20-%20copia.jpg?alt=media&token=3b52732c-4b61-4226-8a58-8ed16e0a8ebf",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2017%2Fni%C3%B1os%20ciencias2%20-%20copia.jpg?alt=media&token=31cd6146-d550-44aa-86d6-cc4f10d48093",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2017%2Fni%C3%B1os%20secundaria%20-%20copia.jpg?alt=media&token=d2b5b23b-4e14-445d-a96e-4c48cbf6d5d2"
        );
        imagesTeacher12.forEach(url -> imageRepository.save(new Image(url, "Imagen de la Profesora Julia Cotton", savedTeacher12)));

        Teacher teacher13 = new Teacher("Juan Ríos", "100013", "Profesor de música con énfasis en teoría musical y composición.", subjectG, characteristicsForTeacher1);
        Teacher savedTeacher13 = teacherRepository.save(teacher13);
        List<String> imagesTeacher13 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%204%2F40.png?alt=media&token=62ba44b2-b547-4cfe-b195-3b9450bf2e6c",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%204%2F41.png?alt=media&token=4ac87470-3835-4565-8e01-7c0bd3a77afa",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%204%2F42.png?alt=media&token=93b46240-3041-42b6-ac27-57e3d15383db",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%204%2FProfesor%20Pizarra%20-%20copia.jpg?alt=media&token=357a10ab-af30-4648-8e0f-63c3c14f667b",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%204%2Fclase%20online%20videollamada%20-%20copia.jpg?alt=media&token=fe547d4b-e27a-4591-93e7-56a0ca12b912"
        );
        imagesTeacher13.forEach(url -> imageRepository.save(new Image(url, "Imagen del Profesor Juan Ríos", savedTeacher13)));

        Teacher teacher14 = new Teacher("María Pérez", "100014", "Veterana en pedagogía y estrategias de gestión educativa.", subjectH, characteristicsForTeacher2);
        Teacher savedTeacher14 = teacherRepository.save(teacher14);
        List<String> imagesTeacher14 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2016%2FG.jpg?alt=media&token=bfa15209-66a9-42a6-9745-83c0f6f3fd57",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2016%2Fclase%20online%20videollamada%20-%20copia.jpg?alt=media&token=ee9b281f-50ca-477a-9d0f-56a6d48c1861",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2016%2Fni%C3%B1os%20ciencias%20-%20copia.jpg?alt=media&token=56f50ab1-5a09-4107-83f7-fb1258e454ca",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2016%2Fni%C3%B1os%20levantando%20mano%20-%20copia.jpg?alt=media&token=0bfb83d7-52dc-4a9f-87db-d5932ae6cdc3",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2016%2Fni%C3%B1os%20primaria%20-%20copia.jpg?alt=media&token=570efca4-b852-4dad-8de4-043a7f511ef0"
        );
        imagesTeacher14.forEach(url -> imageRepository.save(new Image(url, "Imagen de la Profesora María Pérez", savedTeacher14)));

        Teacher teacher15 = new Teacher("José Ortiz", "100015", "Experto en economía y administración de empresas educativas.", subjectG, characteristicsForTeacher3);
        Teacher savedTeacher15 = teacherRepository.save(teacher15);
        List<String> imagesTeacher15 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%203%2F30.png?alt=media&token=0c1b1609-d2c0-4707-b841-fbc288709013",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%203%2F31.png?alt=media&token=889b9735-7685-4389-83b6-3b08904746bd",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%203%2F32.png?alt=media&token=a7fc357e-3e6c-4e61-a359-6512c1c58998",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%203%2Fclase%20online%20computador.jpg?alt=media&token=8012740a-ff10-4302-97e6-35639c38ba7d",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%203%2Fni%C3%B1os%20ciencias2%20-%20copia.jpg?alt=media&token=8a549957-a459-4079-94e2-b8b5cdf72d5d"
        );
        imagesTeacher15.forEach(url -> imageRepository.save(new Image(url, "Imagen del Profesor José Ortiz", savedTeacher15)));

        Teacher teacher16 = new Teacher("Laura Giménez", "100016", "Enseña habilidades de comunicación efectiva y oratoria.", subjectG, characteristicsForTeacher1);
        Teacher savedTeacher16 = teacherRepository.save(teacher16);
        List<String> imagesTeacher16 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2014%2FE.jpg?alt=media&token=8f76097b-1d5c-430f-b317-8be14bfd680a",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2014%2FPizarra%20matem%C3%A1tica%20-%20copia.jpg?alt=media&token=7e887a06-7089-4c32-a0c4-c5c687f3ae5b",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2014%2Fni%C3%B1os%20ciencias%20-%20copia.jpg?alt=media&token=1322f818-c64d-4f1e-a21c-1f4a5d9845d5",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2014%2Fni%C3%B1os%20ciencias2%20-%20copia.jpg?alt=media&token=00ece329-ce46-433e-848b-c998c2789ab2",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2014%2Fni%C3%B1os%20secundaria%20-%20copia.jpg?alt=media&token=72f22777-7967-45ca-be42-a91882a552d7"
        );
        imagesTeacher16.forEach(url -> imageRepository.save(new Image(url, "Imagen de la Profesora Laura Giménez", savedTeacher16)));

        Teacher teacher17 = new Teacher("Carla Ruiz", "100017", "Profesor de tecnología e informática aplicada a la educación.", subjectH, characteristicsForTeacher2);
        Teacher savedTeacher17 = teacherRepository.save(teacher17);
        List<String> imagesTeacher17 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2010%2FA.png?alt=media&token=80639887-c17e-44f9-8bfa-c8b45576b1cf",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2010%2Fni%C3%B1os%20levantando%20mano%20-%20copia.jpg?alt=media&token=d4c00dc4-4704-4366-b619-ad0812a63be6",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2010%2Fyoga%20online.jpg?alt=media&token=b1b768ed-21d8-4c56-8f61-446b52a4c85f",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2010%2Fyoga.jpg?alt=media&token=09f5b68d-d937-47c9-915d-bc4db75f2a5c",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2010%2Fyoga2.jpg?alt=media&token=7eaf4b6f-0321-47b3-b54b-85e35c383206"
        );
        imagesTeacher17.forEach(url -> imageRepository.save(new Image(url, "Imagen del Profesor Carla Ruiz", savedTeacher17)));

        Teacher teacher18 = new Teacher("Ana Torres", "100018", "Especializada en educación especial y apoyo diferenciado.", subjectG, characteristicsForTeacher3);
        Teacher savedTeacher18 = teacherRepository.save(teacher18);
        List<String> imagesTeacher18 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2013%2FD.png?alt=media&token=d781d698-b6c5-429f-b01b-d1dbe17a0586",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2013%2FD1.png?alt=media&token=ffcf36d7-6b99-4f03-ae98-7223c8c04aeb",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2013%2FD2.png?alt=media&token=a646279f-c95e-4bba-b5e2-0bdaeb9d5304",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2013%2FProfesora%2017.jpg?alt=media&token=8e15316b-7bc4-4f69-8aba-2aea42cc2489",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2013%2Fni%C3%B1os%20levantando%20mano%20-%20copia.jpg?alt=media&token=ea5b6574-dc36-4e63-ba93-d8914facf89f"
        );
        imagesTeacher18.forEach(url -> imageRepository.save(new Image(url, "Imagen de la Profesora Ana Torres", savedTeacher18)));

        Teacher teacher19 = new Teacher("Luisa Méndez", "100019", "Profesor de ciencias sociales con enfoque en sociología educativa.", subjectG, characteristicsForTeacher1);
        Teacher savedTeacher19 = teacherRepository.save(teacher19);
        List<String> imagesTeacher19 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2011%2FB.png?alt=media&token=fefd5bbf-f30e-4082-8a2a-02850bad60e4",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2011%2FB1.png?alt=media&token=cdef305c-823d-46d4-8274-e56c524e3199",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2011%2FProfesoraPizarra2%20grupo.jpg?alt=media&token=91bc0823-72be-4a93-944e-69d29a64b173",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2011%2Fclase%20online%20videollamada%20-%20copia.jpg?alt=media&token=12b3ac00-2269-4300-8a06-408709aebe0b",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2011%2Fni%C3%B1os%20levantando%20mano%20-%20copia.jpg?alt=media&token=4fc5d63e-fda8-4bc9-8224-b9315919944c"
        );
        imagesTeacher19.forEach(url -> imageRepository.save(new Image(url, "Imagen del Profesor Luis Méndez", savedTeacher19)));

        Teacher teacher20 = new Teacher("Park Min Young", "100020", "Imparte conocimientos en filosofía y pensamiento crítico.", subjectH, characteristicsForTeacher2);
        Teacher savedTeacher20 = teacherRepository.save(teacher20);
        List<String> imagesTeacher20 = List.of(
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2012%2FC.png?alt=media&token=500b78ef-d035-415f-9c4a-2faf57309ae5",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2012%2FC1.png?alt=media&token=ecbc06fd-282b-450d-bfab-6c1f47a8ad2b",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2012%2FC2.png?alt=media&token=59ae7714-0399-4823-b541-8a0c8cf1a03d",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2012%2FProfesora%20pizarra.jpg?alt=media&token=eed36330-d167-453f-a2b0-23c11755451f",
                "https://firebasestorage.googleapis.com/v0/b/tutor-link-298b4.appspot.com/o/images%2FProfesores%2FProfesor%2012%2Fclase%20online%20videollamada%20-%20copia.jpg?alt=media&token=1886a9e3-e895-48d4-ac98-94d755940ffa"
        );
        imagesTeacher20.forEach(url -> imageRepository.save(new Image(url, "Imagen de la Profesora Park Min", savedTeacher20)));


        Random random = new Random();
        LocalDate startDay = LocalDate.now();

        List<Teacher> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teachers::add);

        log.info("Número de profesores cargados: {}", teachers.size());

        if (teachers.isEmpty()) {
            log.warn("No hay profesores disponibles para asignar horarios.");
            return;
        }

        int midPoint = teachers.size() / 2;
        List<Teacher> firstShiftTeachers = teachers.subList(0, midPoint);
        List<Teacher> secondShiftTeachers = teachers.subList(midPoint, teachers.size());

        assignAvailability(firstShiftTeachers, startDay, LocalTime.of(9, 0), LocalTime.of(17, 0));
        assignAvailability(secondShiftTeachers, startDay, LocalTime.of(14, 0), LocalTime.of(20, 0));
    }

    private void assignAvailability(List<Teacher> teachers, LocalDate startDay, LocalTime startTime, LocalTime endTime) {
        LocalDate endDay = startDay.plusMonths(2);
        LocalDate currentDate = startDay;

        while (!currentDate.isAfter(endDay)) {
            if (currentDate.getDayOfWeek().getValue() <= 5) { // Verifica si es día laboral
                for (Teacher teacher : teachers) {
                    Availability availability = new Availability();
                    availability.setTeacher(teacher);
                    availability.setDate(currentDate);
                    availability.setStartTime(startTime);
                    availability.setEndTime(endTime);

                    availabilityRepository.save(availability);

                    log.info("Disponibilidad guardada para el profesor: {} en fecha: {}", teacher.getName(), currentDate);
                }
            }
            currentDate = currentDate.plusDays(1); // Avanza al siguiente día
        }
    }
}

