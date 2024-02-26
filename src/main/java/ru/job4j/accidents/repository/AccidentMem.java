package ru.job4j.accidents.repository;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@NoArgsConstructor
public class AccidentMem implements AccidentRepository {

    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>() {
        {
            put(1, new Accident(1, "Неправильная парковка", "Парковка на газоне", "Ленина 24",
                    new AccidentType(1, "Две машины")));
            put(2, new Accident(2, "Превышение скорости", "Превышена скорость в черте города",
                    "Строителей 5", new AccidentType(1, "Две машины")));
            put(3, new Accident(3, "Езда без прав", "Отсутствие документов", "Металлургов 15",
                    new AccidentType(1, "Две машины")));
            put(4, new Accident(4, "Неисправность световых приборов", "Не работают дневные ходовые огни",
                    "Проспект Мира 8", new AccidentType(1, "Две машины")));
        }
    };

    private final AtomicInteger nextId = new AtomicInteger(accidents.size() + 1);

    @Override
    public Accident create(Accident accident) {
        accident.setId(nextId.getAndIncrement());
        return accidents.put(accident.getId(), accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public boolean update(Accident accident) {
        return accidents.computeIfPresent(accident.getId(), (id, oldAccident) -> {
            return new Accident(
                    oldAccident.getId(), accident.getName(), accident.getText(), accident.getAddress(), accident.getType()
            );
        }) != null;
    }
}
