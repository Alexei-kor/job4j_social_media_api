package ru.job4j.socialmediaapi.service;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Post;

import java.util.List;

public interface PostService {

    @Transactional
    void create(Post post);

    @Transactional
    int update(Post post);

    @Transactional
    int delete(Post post);

}
