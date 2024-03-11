package ru.job4j.accidents.service;

import ru.job4j.accidents.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Optional<User> save(User user);
    Optional<User> findById(int id);
    Collection<User> findAll();
}
