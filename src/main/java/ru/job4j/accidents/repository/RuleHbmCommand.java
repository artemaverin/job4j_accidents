package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.*;

@Repository
@AllArgsConstructor
@Primary
public class RuleHbmCommand implements RuleRepository {

    private CrudRepository crudRepository;

    @Override
    public Collection<Rule> findAll() {
        return crudRepository.query("FROM Rule order by id", Rule.class);
    }

    @Override
    public Set<Rule> findByIds(List<Integer> rIds) {
        return new HashSet<>(crudRepository
                .query("from Rule f where f.id IN :fIds", Rule.class, Map.of("fIds", rIds)));
    }
}
