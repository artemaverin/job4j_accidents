package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentDataRepository;
import ru.job4j.accidents.repository.AccidentHbmCommand;
import ru.job4j.accidents.repository.AccidentHibernate;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private AccidentDataRepository accidentRepository;

    @Override
    public Accident create(Accident accident) {
        return accidentRepository.save(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return (Collection<Accident>) accidentRepository.findAll();
    }

    @Override
    public boolean update(Accident accident) {
        return accidentRepository.save(accident) != null;
    }
}
