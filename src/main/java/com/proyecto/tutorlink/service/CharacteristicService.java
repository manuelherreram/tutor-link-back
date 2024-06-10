package com.proyecto.tutorlink.service;

import com.proyecto.tutorlink.entity.Characteristic;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
    public class CharacteristicService {
    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Transactional
    public Characteristic addCharacteristic(Characteristic characteristic) {
        if (characteristicRepository.existsByName(characteristic.getName())) {
            throw new IllegalStateException("A characteristic with the same id already exists");
        }
        return characteristicRepository.save(characteristic);
    }

    public List<Characteristic> getAllCharacteristics() {
        return characteristicRepository.findAll();
    }

    public Characteristic getCharacteristicById(Long id) throws CustomException {
        return characteristicRepository.findById(id).orElseThrow(() -> new CustomException("Characteristic not found"));
    }

    public Characteristic actualizarCharacteristic(Characteristic characteristicRecibido) {
        Characteristic characteristicAActualizar = characteristicRepository.findById((long) characteristicRecibido.getId()).orElse(null);


        if (characteristicAActualizar != null) {
            characteristicAActualizar = characteristicRecibido;
            characteristicRepository.save(characteristicAActualizar);

        } else {
            throw new IllegalStateException("No se ha encontrado el characteristic con id " + characteristicRecibido.getId());
        }


        return characteristicAActualizar;
    }

    @Transactional
    public void deleteCharacteristic(Long id) {
        Characteristic characteristic = characteristicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Characteristic not found"));

        // Remove the characteristic from all teachers
        for (Teacher teacher : characteristic.getTeachers()) {
            teacher.getCharacteristics().remove(characteristic);
        }

        characteristicRepository.delete(characteristic);
    }
}
