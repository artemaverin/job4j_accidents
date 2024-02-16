package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.Collection;
@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private AccidentRepository accidentRepository;

    @Override
    public Collection<Accident> findAll() {
        return accidentRepository.findAll();
    }
}
