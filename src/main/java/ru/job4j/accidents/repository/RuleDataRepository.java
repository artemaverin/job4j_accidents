package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RuleDataRepository extends CrudRepository<Rule, Integer> {
    Collection<Rule> findAll();
    @Query("from Rule f where f.id IN :ids")
    Set<Rule> findAllById(List<Integer> ids);
}
