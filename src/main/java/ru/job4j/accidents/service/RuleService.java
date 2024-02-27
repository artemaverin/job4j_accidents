package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Set;

public interface RuleService {
    Collection<Rule> findAll();
    Set<Rule> findByIds(String[] ids);
}
