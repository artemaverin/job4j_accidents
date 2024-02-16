package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Repository
@NoArgsConstructor
public class AccidentMem implements AccidentRepository {

    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>() {
        {
            put(1, new Accident(1, "Неправильная парковка", "Парковка на газоне", "Ленина 24"));
            put(2, new Accident(2, "Превышение скорости", "Превышена скорость в черте города", "Строителей 5"));
            put(3, new Accident(3, "Езда без прав", "Отсутствие документов", "Металлургов 15"));
            put(4, new Accident(4, "Неисправность световых приборов", "Не работают дневные ходовые огни", "Проспект Мира 8"));
        }
    };

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }
}
