package com.proyecto.tutorlink.service;

import com.proyecto.tutorlink.entity.Characteristic;
import com.proyecto.tutorlink.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

    @Service
    public class CharacteristicService {
        @Autowired
        private CharacteristicRepository characteristicRepository;

    }
