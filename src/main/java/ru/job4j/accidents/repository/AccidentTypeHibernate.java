package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate implements AccidentTypeRepository {

    private final SessionFactory sf;

    @Override
    public Collection<AccidentType> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType f where f.id = :fId", AccidentType.class)
                            .setParameter("fId", id).uniqueResultOptional();
        }
    }
}
