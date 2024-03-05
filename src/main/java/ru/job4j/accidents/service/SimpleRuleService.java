package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleDataRepository;
import ru.job4j.accidents.repository.RuleHbmCommand;
import ru.job4j.accidents.repository.RuleHibernate;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SimpleRuleService implements RuleService {

    private RuleDataRepository ruleRepository;

    @Override
    public Collection<Rule> findAll() {
        return (Collection<Rule>) ruleRepository.findAll();
    }

    @Override
    public Set<Rule> findByIds(List<Integer> rIds) {
        return new HashSet<>((Collection<Rule>) ruleRepository.findAllById(rIds));
    }
}
