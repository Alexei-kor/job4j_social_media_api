package ru.job4j.socialmediaapi.service;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.*;
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
class RequestServiceDBTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserService userService;

    @Autowired
    RequestService requestService;

    @Autowired
    SubscriptionService subscriptionService;

    @AfterEach
    public void setUp() {
        requestRepository.deleteAll();
        subscriptionRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void whenCreateUsersAndAddRequest() {
        User user1 = new User("vasya", "111@ya.ru", "123");
        User user2 = new User("sasha", "222@ya.ru", "123");
        User user3 = new User("petya", "333@ya.ru", "123");

        Request request1 = new Request(user1, user2, Status.SEND);
        Request request2 = new Request(user1, user3, Status.SEND);
        Request request3 = new Request(user2, user3, Status.SEND);

        Subscription subscription1 = new Subscription(user1, user2);
        Subscription subscription2 = new Subscription(user1, user3);
        Subscription subscription3 = new Subscription(user2, user3);

        userService.create(user1);
        userService.create(user2);
        userService.create(user3);

        subscriptionService.create(subscription1);
        subscriptionService.create(subscription2);
        subscriptionService.create(subscription3);

        requestService.create(request1);
        requestService.create(request2);
        requestService.create(request3);

        var found = requestRepository.findFriendsByIDOwner(user1.getId(), Status.SEND);
        assertThat(found.size()).isEqualTo(2);

        var foundSub = subscriptionRepository.findAll();
        assertThat(foundSub.size()).isEqualTo(3);
    }
}