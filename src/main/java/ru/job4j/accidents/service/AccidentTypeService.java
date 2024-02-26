package ru.job4j.accidents.service;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;

public interface AccidentTypeService {
    Map<Integer, AccidentType> findAll();
}
