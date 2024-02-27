package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AccidentTypeRepository {
    Collection<AccidentType> findAll();
    Optional<AccidentType> findById(int id);
}
