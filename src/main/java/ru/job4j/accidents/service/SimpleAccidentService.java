package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private AccidentRepository accidentJdbcTemplate;

    @Override
    public Accident create(Accident accident) {
        return accidentJdbcTemplate.create(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentJdbcTemplate.findById(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentJdbcTemplate.findAll();
    }

    @Override
    public boolean update(Accident accident) {
        return accidentJdbcTemplate.update(accident);
    }
}
