package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Primary
public class AccidentHbmCommand implements AccidentRepository {

    private CrudRepository crudRepository;

    @Override
    public Accident create(Accident accident) {
        crudRepository.run(session -> session.persist(accident));
        return accident;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return crudRepository
                .optional("SELECT DISTINCT f from Accident f JOIN FETCH f.rules where f.id = :fId",
                        Accident.class,
                        Map.of("fId", id));
    }

    @Override
    public Collection<Accident> findAll() {
        return crudRepository
                .query("SELECT DISTINCT f from Accident f JOIN FETCH f.rules order by f.id", Accident.class);
    }

    @Override
    public boolean update(Accident accident) {
        try {
            crudRepository.run(session -> session.update(accident));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
