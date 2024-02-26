package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Map;

public interface AccidentTypeRepository {
    Map<Integer, AccidentType> findAll();
}
