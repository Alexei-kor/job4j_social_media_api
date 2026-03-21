package ru.job4j.socialmediaapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.model.Request;
import ru.job4j.socialmediaapi.model.Subscription;
import ru.job4j.socialmediaapi.repository.RequestRepository;
import ru.job4j.socialmediaapi.repository.SubscriptionRepository;

@Service
@AllArgsConstructor
public class SubscriptionServiceDB implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public void create(Subscription request) {
        subscriptionRepository.save(request);
    }

}
