package ru.job4j.socialmediaapi.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.Subscription;
import ru.job4j.socialmediaapi.model.User;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SubscriptionRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Test
	public void whenAddSubscriptionAndFindByOwner() {
		subscriptionRepository.deleteAll();
		userRepository.deleteAll();

		User user1 = new User("vasya", "abc@ya.ru", "123");
		User user2 = new User("petya", "cde@ya.ru", "123");
		User user3 = new User("sasha", "abc@mail.ru", "123");
		User user4 = new User("misha", "abc@mail.ru", "123");

		Subscription subscription1 = new Subscription(user3, user4);
		Subscription subscription2 = new Subscription(user3, user2);
		Subscription subscription3 = new Subscription(user1, user3);

		List<Subscription> set1 = List.of(subscription1, subscription2);
		user3.setSubscriptions(set1);

		List<Subscription> set2 = List.of(subscription3);
		user1.setSubscriptions(set2);

		userRepository.saveAll(List.of(user1, user2, user3, user4));
		subscriptionRepository.saveAll(List.of(subscription1, subscription2, subscription3));

		var foundSub = subscriptionRepository.findSubscribersByIDOwner(user3.getId());
		assertThat(foundSub).hasSize(2);
	}

}
