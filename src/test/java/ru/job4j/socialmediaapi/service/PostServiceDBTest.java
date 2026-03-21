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
import java.util.Optional;
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
    public void whenCreateUserImagesPost() {
        User user = new User("vasya", "vasya@ya.ru", "123");

        Image image = new Image("Веселый жираф", "jpg", "тут картинка веселого жирафа");
        Image image2 = new Image("Солнце", "jpg", "тут картинка солнца");
        Image image3 = new Image("Снег", "jpg", "тут картинка снега");

        Post post = new Post(user, LocalDateTime.of(2026, 3, 1, 0, 0), "первый пост", "Про авто");
        post.setImages(Set.of(image, image2, image3));

        userService.create(user);
        imageService.create(image);
        imageService.create(image2);
        imageService.create(image3);
        postService.create(post);

        var found = postRepository.findAll();
        assertThat(found.size()).isEqualTo(1);
    }

    @Test
    public void whenCreateUpdatePost() {
        User user = new User("vasya", "vasya@ya.ru", "123");

        Image image = new Image("Веселый жираф", "jpg", "тут картинка веселого жирафа");
        Image image2 = new Image("Солнце", "jpg", "тут картинка солнца");

        Post post = new Post(user, LocalDateTime.of(2026, 3, 1, 0, 0), "первый пост", "Про авто");
        post.setImages(Set.of(image, image2));

        userService.create(user);
        imageService.create(image);
        imageService.create(image2);
        postService.create(post);

        var found = postRepository.findAll();
        assertThat(found.size()).isEqualTo(1);

        post.setHead("Измененный заголовок поста");
        postService.update(post);

        Optional<Post> found1 = postRepository.findById(post.getId());
        assertThat(found1.get().getHead()).isEqualTo("Измененный заголовок поста");
    }

    @Test
    public void whenCreateDeletePost() {
        User user = new User("vasya", "vasya@ya.ru", "123");

        Post post = new Post(user, LocalDateTime.of(2026, 3, 1, 0, 0), "первый пост", "Про авто");

        userService.create(user);
        postService.create(post);

        var found = postRepository.findAll();
        assertThat(found.size()).isEqualTo(1);

        postService.delete(post);

        Optional<Post> found1 = postRepository.findById(post.getId());
        assertThat(found1.isPresent()).isFalse();
    }

}