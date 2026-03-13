package ru.job4j.socialmediaapi.repository;

import org.assertj.core.internal.Conditions;
import org.hamcrest.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	@BeforeEach
	public void setUp() {
		userRepository.deleteAll();
		postRepository.deleteAll();
	}

	@Test
	public void whenSavePost_thenFindById() {
		User user = new User("vasya", "abc@ya.ru", "123");
		Post post1 = new Post(user, LocalDateTime.now(), "важный первый пост", "История одного поста...");
		userRepository.save(user);
		postRepository.save(post1);
		var foundUser = userRepository.findById(user.getId());
		assertThat(foundUser).isPresent();
		assertThat(foundUser.get().getName()).isEqualTo("vasya");
	}

	@Test
	public void whenFindAll_thenReturnAllPersons() {
		User user = new User("vasya1", "abc@ya.ru", "123");
		Post post1 = new Post(user, LocalDateTime.now(), "важный первый пост", "История одного поста...");
		Post post2 = new Post(user, LocalDateTime.now(), "Второй пост", "Про погоду");
		userRepository.save(user);
		postRepository.save(post1);
		postRepository.save(post2);
		var a = postRepository.findById(1l);
		assertThat(a.isPresent());
		//assertThat(a.get().getHead()).isEqualTo("Второй пост");
	}
}
