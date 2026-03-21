package ru.job4j.socialmediaapi.service;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Status;
import ru.job4j.socialmediaapi.model.Subscription;
import ru.job4j.socialmediaapi.model.User;

public interface SubscriptionService {

    @Transactional
    void sendFriendRequest(User from, User to);

    @Transactional
    int updateStatusFriendRequest(User from, User to, Status status);

}
