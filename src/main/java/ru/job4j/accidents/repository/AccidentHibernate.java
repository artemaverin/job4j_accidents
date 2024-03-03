package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Primary
public class AccidentHibernate implements AccidentRepository {

    private final SessionFactory sf;


    @Override
    public Accident create(Accident accident) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(accident);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return accident;
    }

    @Override
    public Optional<Accident> findById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("SELECT DISTINCT f from Accident f JOIN FETCH f.rules where f.id = :fId", Accident.class)
                    .setParameter("fId", id).uniqueResultOptional();
        }
    }

    @Override
    public Collection<Accident> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("SELECT DISTINCT f from Accident f JOIN FETCH f.rules order by f.id", Accident.class)
                    .list();
        }
    }

    @Override
    public boolean update(Accident accident) {
        var session = sf.openSession();
        Object result = null;
        try {
            session.beginTransaction();
            result = session.merge(accident);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result != null;
    }
}
