package ru.job4j.socialmediaapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.*;

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
}
