package ru.job4j.accidents.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accidents.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("from User where username = :name")
    Optional<User> findByName(@Param("name") String name);
}
