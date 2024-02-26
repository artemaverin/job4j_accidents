package ru.job4j.accidents.repository;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.HashMap;
import java.util.Map;
@Repository
@NoArgsConstructor
public class AccidentTypeMem implements AccidentTypeRepository {

    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>() {
        {
            put(1, new AccidentType(1, "Две машины"));
            put(2, new AccidentType(2, "Машина и человек"));
            put(3, new AccidentType(3, "Машина и велосипед"));
        }
    };

    @Override
    public Map<Integer, AccidentType> findAll() {
        return accidentTypes;
    }
}
