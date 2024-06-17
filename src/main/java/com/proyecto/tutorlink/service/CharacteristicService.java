package com.proyecto.tutorlink.service;

import com.proyecto.tutorlink.entity.Characteristic;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.proyecto.tutorlink.dto.CharacteristicDto;
import com.proyecto.tutorlink.dto.CharacteristicInputDto;
import java.util.List;

@Service
    public class CharacteristicService {
    @Autowired
    private CharacteristicRepository characteristicRepository;


    // En CharacteristicService
    public CharacteristicDto addCharacteristic(CharacteristicInputDto characteristicInputDto) {
        if (characteristicRepository.existsByName(characteristicInputDto.getName())) {
            throw new IllegalStateException("A characteristic with the same name already exists");
        }
        Characteristic characteristic = new Characteristic(characteristicInputDto.getName(), characteristicInputDto.getUrl());
        characteristic = characteristicRepository.save(characteristic);
        return new CharacteristicDto(characteristic);
    }


    public List<Characteristic> getAllCharacteristics() {
        return characteristicRepository.findAll();
    }

    public Characteristic getCharacteristicById(Long id) throws CustomException {
        return characteristicRepository.findById(id).orElseThrow(() -> new CustomException("Characteristic not found"));
    }

   public Characteristic updateCharacteristic(Long id, CharacteristicInputDto characteristicInputDto) {
        Characteristic characteristic = characteristicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Characteristic not found"));

        characteristic.setName(characteristicInputDto.getName());
        characteristic.setUrl(characteristicInputDto.getUrl());

        return characteristicRepository.save(characteristic);
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
