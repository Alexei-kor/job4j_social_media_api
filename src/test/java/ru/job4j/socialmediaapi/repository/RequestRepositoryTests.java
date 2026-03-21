package ru.job4j.socialmediaapi.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.Request;
import ru.job4j.socialmediaapi.model.Status;
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

	@AfterEach
	public void setUp() {
		requestRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	public void whenSaveUserAddSubscribersAndFindSubscribers() {

		User user1 = new User("vasya", "abc@ya.ru", "123");
		User user2 = new User("petya", "cde@ya.ru", "123");
		User user3 = new User("sasha", "abc@mail.ru", "123");
		User user4 = new User("misha", "abc@mail.ru", "123");

		userRepository.saveAll(List.of(user1, user2, user3, user4));

		Request request1 = new Request(user1, user2, Status.SEND);
		Request request2 = new Request(user1, user3, Status.SEND);
		Request request3 = new Request(user1, user4, Status.APPROVE);

		requestRepository.saveAll(List.of(request1, request2, request3));

		var found = requestRepository.findFriendsByIDOwner(user1.getId(), Status.APPROVE);
		assertThat(found).hasSize(1);
	}

}
