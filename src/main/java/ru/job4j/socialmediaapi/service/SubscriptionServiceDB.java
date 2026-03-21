package ru.job4j.socialmediaapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.model.*;
import ru.job4j.socialmediaapi.repository.*;

@Service
@AllArgsConstructor
public class SubscriptionServiceDB implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;

    @Override
    public void sendFriendRequest(User from, User to) {
        if (from.getId() == null) {
            userRepository.save(from);
        }
        if (to.getId() == null) {
            userRepository.save(to);
        }
        Request request = new Request(from, to, Status.SEND);
        Subscription subscription = new Subscription(from, to);
        requestRepository.save(request);
        subscriptionRepository.save(subscription);
    }

    @Override
    public int updateStatusFriendRequest(User from, User to, Status status) {
        int count = requestRepository.updateStatusRequest(from, to, status);
        if (status.equals(Status.APPROVE)) {
            Request request = new Request(to, from, status);
            Subscription subscription = new Subscription(to, from);
            requestRepository.save(request);
            subscriptionRepository.save(subscription);
        } else if (status.equals(Status.REJECT)) {
            requestRepository.updateStatusRequest(to, from, status);
            subscriptionRepository.deleteSubscription(to.getId(), from.getId());
        }
        return count;
    }
}
