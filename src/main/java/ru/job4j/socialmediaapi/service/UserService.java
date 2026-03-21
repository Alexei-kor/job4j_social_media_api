package ru.job4j.socialmediaapi.service;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;

import java.util.List;

public interface UserService {

    @Transactional
    void create(User user);

}
