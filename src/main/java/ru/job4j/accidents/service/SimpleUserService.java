package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {
    private UserRepository userRepository;
    private static final Logger LOG = LogManager.getLogger(SimpleUserService.class.getName());

    @Override
    public Optional<User> save(User user) {
        Optional<User> optionalUser = Optional.empty();
        try {
            userRepository.save(user);
            optionalUser = Optional.of(user);
        } catch (Exception e) {
             LOG.info("Сохранение не удалось");
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }
}
