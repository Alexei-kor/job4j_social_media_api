package ru.job4j.socialmediaapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.repository.ImageRepository;
import ru.job4j.socialmediaapi.repository.PostRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageServiceDB implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public void create(List<Image> images) {
        images.forEach(imageRepository::save);

    }
}
