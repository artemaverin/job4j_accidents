package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.repository.AccidentTypeRepository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Map;

@Service
@AllArgsConstructor
public class SimpleAccidentTypeService implements AccidentTypeService {

    private AccidentTypeRepository accidentTypeRepository;

    @Override
    public Map<Integer, AccidentType> findAll() {
        return accidentTypeRepository.findAll();
    }
}
