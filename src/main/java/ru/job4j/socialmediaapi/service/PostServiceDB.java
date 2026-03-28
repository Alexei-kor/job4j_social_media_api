package ru.job4j.socialmediaapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.dto.PostMapper;
import ru.job4j.socialmediaapi.dto.PostdDTO;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.ImageRepository;
import ru.job4j.socialmediaapi.repository.PostRepository;
import ru.job4j.socialmediaapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceDB implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final PostMapper postMapper;

    @Override
    public void create(Long ownerId, Post post) {
        post.setOwner(userRepository.findById(ownerId).get());
        postRepository.save(post);
    }

    @Override
    public boolean update(Post post) {
        return postRepository.updateHeadAndTextPost(post.getId(), post.getHead(), post.getText()) > 0L;
    }

    @Override
    public boolean delete(Long postID) {
        return postRepository.deletePost(postID) > 0L;
    }

    public Optional<Post> get(Long id) {
        return postRepository.findById(id);
    }

    public List<PostdDTO> findByOwnerInOrderByPeriodDesc(List<Long> listIDs) {
        List<PostdDTO> rez = new ArrayList<>();
        List<User> foundUsers = userRepository.findAllById(listIDs);
        for (User user : foundUsers) {
            Page<Post> findPosts = postRepository.findByOwnerInOrderByPeriodDesc(List.of(user), org.springframework.data.domain.Pageable.ofSize(50));
            rez.add(postMapper.getModelFromEntity(user, findPosts.get().toList()));
        }
        return rez;
    }
}
