package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@AllArgsConstructor
public class RuleHibernate implements RuleRepository {

    private final SessionFactory sf;

    @Override
    public Collection<Rule> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }

    @Override
    public Set<Rule> findByIds(List<Integer> rIds) {
        try (Session session = sf.openSession()) {
            return new HashSet<>(session
                    .createQuery("from Rule f where f.id IN :fIds", Rule.class)
                    .setParameter("fIds", rIds).getResultList());
        }
    }
}
