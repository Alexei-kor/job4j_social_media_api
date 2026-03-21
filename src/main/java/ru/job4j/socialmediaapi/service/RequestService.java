package ru.job4j.socialmediaapi.service;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Request;

public interface RequestService {

    @Transactional
    void create(Request request);

    @Transactional
    int update(Request request);

}
