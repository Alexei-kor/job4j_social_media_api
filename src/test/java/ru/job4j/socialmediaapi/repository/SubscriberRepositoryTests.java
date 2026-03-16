package ru.job4j.socialmediaapi.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.Subscriber;
import ru.job4j.socialmediaapi.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SubscriberRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SubscriberRepository subscriberRepository;

	@Test
	public void whenSaveUserAddSubscribersAndFindSubscribers() {
		subscriberRepository.deleteAll();
		userRepository.deleteAll();

		User user1 = new User("vasya", "abc@ya.ru", "123");
		User user2 = new User("petya", "cde@ya.ru", "123");
		User user3 = new User("sasha", "abc@mail.ru", "123");
		User user4 = new User("misha", "abc@mail.ru", "123");

		Subscriber subscriber2 = new Subscriber(user2);
		Subscriber subscriber3 = new Subscriber(user3);
		Subscriber subscriber4 = new Subscriber(user4);

		Set<Subscriber> set1 = new HashSet<>(List.of(subscriber2));
		user1.setSubscribers(set1);

		Set<Subscriber> set2 = new HashSet<>(List.of(subscriber3));
		user2.setSubscribers(set2);

		Set<Subscriber> set3 = new HashSet<>(List.of(subscriber4));
		user3.setSubscribers(set3);

		userRepository.saveAll(List.of(user1, user2, user3, user4));
		subscriberRepository.saveAll(List.of(subscriber2, subscriber3, subscriber4));

		var foundSub = subscriberRepository.findSubscribersByIDOwner(user1.getId());
		assertThat(foundSub).hasSize(1);
	}

}
