package ru.job4j.socialmediaapi.service;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;

public interface UserService {

    @Transactional
    void create(User user);

    @Transactional
    boolean update(User user);

    @Transactional
    boolean delete(Long id);

}
