package ru.job4j.socialmediaapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.PostRepository;
import ru.job4j.socialmediaapi.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceDB implements UserService {

    private final UserRepository userRepository;

    @Override
    public void create(User user) {
        userRepository.save(user);
    }
}
