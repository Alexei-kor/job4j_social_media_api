package ru.job4j.socialmediaapi.service;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Post;

import java.util.List;

public interface ImageService {

    @Transactional
    void create(List<Image> images);

}
