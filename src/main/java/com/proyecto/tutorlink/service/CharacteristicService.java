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

   /* public List<Characteristic> getAllCharacteristic() {
        return CharacteristicRepository.findAll();
    }*/
    @Transactional
        public Characteristic addCharacteristic(Characteristic characteristic) {
            if (characteristicRepository.existsById(characteristic.getId())) {
                throw new IllegalStateException("A characteristic with the same id already exists");
            }
            return characteristicRepository.save(characteristic);
        }


    }
