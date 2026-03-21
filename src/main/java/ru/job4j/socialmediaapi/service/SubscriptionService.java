package ru.job4j.socialmediaapi.service;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Subscription;

public interface SubscriptionService {

    @Transactional
    void create(Subscription subscription);

}
