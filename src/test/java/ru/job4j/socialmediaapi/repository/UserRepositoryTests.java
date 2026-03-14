package ru.job4j.socialmediaapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	public void setUp() {

	}

	@Test
	public void whenSaveUserthenFindById() {
		User user = new User("vasya", "abc@ya.ru", "123");
		userRepository.save(user);
		var foundUser = userRepository.findById(user.getId());
		assertThat(foundUser).isPresent();
		assertThat(foundUser.get().getName()).isEqualTo("vasya");
	}

	@Test
	public void whenSaveUserthenFindByEmail() {
		User user1 = new User("vasya", "abc@ya.ru", "123");
		User user2 = new User("petya", "cde@ya.ru", "123");
		User user3 = new User("sasha", "abc@mail.ru", "123");
		userRepository.saveAll(List.of(user1, user2, user3));
		var foundUser = userRepository.findByEmailLike("%mail%");
		assertThat(foundUser).hasSize(1);
	}
}
