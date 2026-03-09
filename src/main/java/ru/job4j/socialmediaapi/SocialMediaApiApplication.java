package ru.job4j.socialmediaapi;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityProperties;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.PostRepository;
import ru.job4j.socialmediaapi.repository.UserRepository;

import java.time.LocalDate;

@SpringBootApplication
@AllArgsConstructor
public class SocialMediaApiApplication implements CommandLineRunner {

	private UserRepository userRepository;
	private PostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		User user = new User("vasya", "abc@ya.ru", "123");
		LocalDate localDate = LocalDate.now();
		Post post = new Post(user, localDate, localDate, "важный первый пост", "История одного поста...");
		userRepository.save(user);
		postRepository.save(post);
		System.out.println(user.getId());
		userRepository.findAll().forEach(System.out::println);

	}
}
