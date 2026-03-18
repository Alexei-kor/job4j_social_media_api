package ru.job4j.socialmediaapi.service;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.ImageRepository;
import ru.job4j.socialmediaapi.repository.PostRepository;
import ru.job4j.socialmediaapi.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceDBTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    ImageService imageService;

    @AfterEach
    public void setUp() {
        imageRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void whenCreateUsersImagesPosts() {
        User userVasya = new User("vasya", "vasya@ya.ru", "123");
        User userSasha = new User("sasha", "sasha@ya.ru", "123");
        Post post1 = new Post(userSasha, LocalDateTime.of(2026, 3, 1, 0, 0), "первый пост", "Про авто");
        Post post2 = new Post(userSasha, LocalDateTime.of(2026, 2, 1, 0, 0), "второй пост", "Про зверей");
        Post post3 = new Post(userVasya, LocalDateTime.of(2026, 1, 1, 0, 0), "третий пост", "Про погоду");

        Image image = new Image("Веселый жираф", "jpg", "тут картинка веселого жирафа");
        Image image2 = new Image("Солнце", "jpg", "тут картинка солнца");
        Image image3 = new Image("Снег", "jpg", "тут картинка снега");

        post1.setImages(Set.of(image, image2, image3));
        post2.setImages(Set.of(image2, image3));

        userService.create(List.of(userSasha, userVasya));
        imageService.create(List.of(image, image2, image3));
        postService.create(List.of(post1, post2, post3));

        var found = postRepository.findAll();
        assertThat(found.size()).isEqualTo(3);
    }

}