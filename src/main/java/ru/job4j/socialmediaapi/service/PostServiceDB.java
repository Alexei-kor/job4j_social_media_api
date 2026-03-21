package ru.job4j.socialmediaapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.ImageRepository;
import ru.job4j.socialmediaapi.repository.PostRepository;
import ru.job4j.socialmediaapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceDB implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Override
    public void create(Post post) {
        if (post.getOwner().getId() == null) {
            userRepository.save(post.getOwner());
        }
        for (Image image : post.getImages()) {
            if (image.getId() == null) {
                imageRepository.save(image);
            }
        }
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
