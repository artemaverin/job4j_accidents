package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Primary
public class AccidentTypeHbmCommand implements AccidentTypeRepository {

    private CrudRepository crudRepository;

    @Override
    public Collection<AccidentType> findAll() {
        return crudRepository.query("from AccidentType", AccidentType.class);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return crudRepository
                .optional("from AccidentType f where f.id = :fId", AccidentType.class, Map.of("fId", id));
    }
}
