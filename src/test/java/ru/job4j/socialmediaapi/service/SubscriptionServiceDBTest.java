package ru.job4j.socialmediaapi.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.Status;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SubscriptionServiceDBTest {
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    SubscriptionServiceDB subscriptionServiceDB;

    @AfterEach
    public void setUp() {
        subscriptionRepository.deleteAll();
        requestRepository.deleteAll();
    }

    @Test
    public void whenAddFriendRequest() {
        User user1 = new User("user1", "123@ya.ru", "123");
        User user2 = new User("user2", "456@ya.ru", "123");
        subscriptionServiceDB.sendFriendRequest(user1, user2);

        var foundFriends = requestRepository.findFriendsByIDOwner(user1.getId(), Status.SEND);
        assertThat(foundFriends.size()).isEqualTo(1);

        var foundSubsc = subscriptionRepository.findSubscribersByIDOwner(user1.getId());
        assertThat(foundSubsc.size()).isEqualTo(1);
    }

    @Test
    public void whenApproveFriendRequest() {
        User user1 = new User("user1", "123@ya.ru", "123");
        User user2 = new User("user2", "456@ya.ru", "123");
        subscriptionServiceDB.sendFriendRequest(user1, user2);

        subscriptionServiceDB.updateStatusFriendRequest(user1, user2, Status.APPROVE);

        List<Long> foundFriends = requestRepository.findFriendsByIDOwner(user2.getId(), Status.APPROVE);
        assertThat(foundFriends.contains(user1.getId())).isEqualTo(true);

        List<Long> foundSubsc = subscriptionRepository.findSubscribersByIDOwner(user2.getId());
        assertThat(foundSubsc.contains(user1.getId())).isEqualTo(true);
    }
}