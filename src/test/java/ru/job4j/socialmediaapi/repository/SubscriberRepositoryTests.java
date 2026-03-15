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

		Set<User> set1 = new HashSet<>(List.of(user2, user3));
		user1.setSubscribers(set1);

		Subscriber subscriber1 = new Subscriber(user1, user2.getName());
		Subscriber subscriber2 = new Subscriber(user1, user3.getName());

		Subscriber subscriber3 = new Subscriber(user2, user4.getName());
		Set<User> set2 = new HashSet<>(List.of(user4));
		user2.setSubscribers(set2);

		Subscriber subscriber4 = new Subscriber(user4, user1.getName());
		Set<User> set3 = new HashSet<>(List.of(user1));
		user4.setSubscribers(set3);

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		subscriberRepository.saveAll(List.of(subscriber1, subscriber2, subscriber3, subscriber4));

		var foundSub = subscriberRepository.findSubscribersByIDOwner(user1.getId());
		assertThat(foundSub).hasSize(2);
	}

}
