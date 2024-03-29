package ru.job4j.accidents.repository;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.*;

@Repository
@NoArgsConstructor
public class RuleMem implements RuleRepository {

    private final Map<Integer, Rule> rules = new HashMap<>() {
        {
            put(1, new Rule(1, "Статья. 1"));
            put(2, new Rule(2, "Статья. 2"));
            put(3, new Rule(3, "Статья. 3"));
        }
    };

    @Override
    public Collection<Rule> findAll() {
        return rules.values();
    }

    @Override
    public Set<Rule> findByIds(List<Integer> rIds) {
        Set<Rule> ruleIds = new HashSet<>();
        for (Integer s:  rIds) {
            ruleIds.add(rules.get(s));
        }
        return ruleIds;
    }
}
