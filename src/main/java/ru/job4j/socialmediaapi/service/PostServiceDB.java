package ru.job4j.socialmediaapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.repository.PostRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceDB implements PostService {

    private final PostRepository postRepository;

    @Override
    public void create(List<Post> posts) {
        posts.forEach(postRepository::save);
    }
}
