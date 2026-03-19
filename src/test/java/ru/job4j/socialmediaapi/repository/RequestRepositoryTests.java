package ru.job4j.socialmediaapi.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.Request;
import ru.job4j.socialmediaapi.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RequestRepositoryTests {

	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void whenSaveUserAddSubscribersAndFindSubscribers() {
		requestRepository.deleteAll();
		userRepository.deleteAll();

		User user1 = new User("vasya", "abc@ya.ru", "123");
		User user2 = new User("petya", "cde@ya.ru", "123");
		User user3 = new User("sasha", "abc@mail.ru", "123");
		User user4 = new User("misha", "abc@mail.ru", "123");

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);

		Request request1 = new Request(user1, user2, 1);
		Request request2 = new Request(user1, user3, 1);
		Request request3 = new Request(user1, user4, 2);

		requestRepository.save(request1);
		requestRepository.save(request2);
		requestRepository.save(request3);

		List<Request> set1 = List.of(request1, request2, request3);
		user1.setRequests(set1);

		userRepository.save(user1);

		var found = requestRepository.findFriendsByIDOwner(user1.getId());
		assertThat(found).hasSize(1);
	}

}
