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
    public void create(Post post) {
        postRepository.save(post);
    }

    @Override
    public int update(Post post) {
        return postRepository.updateHeadAndTextPost(post.getId(), post.getHead(), post.getText());
    }

    @Override
    public int delete(Post post) {
        return postRepository.deletePost(post.getId());
    }
}
