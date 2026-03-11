package ru.job4j.socialmediaapi;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.job4j.socialmediaapi.model.*;
import ru.job4j.socialmediaapi.repository.*;

import java.time.LocalDateTime;
import java.util.HashSet;

@SpringBootApplication
@AllArgsConstructor
public class SocialMediaApiApplication implements CommandLineRunner {

	private UserRepository userRepository;
	private PostRepository postRepository;
	private ImageRepository imageRepository;
	private RequestRepository requestRepository;
	private SubscriberRepository subscriberRepository;
	private MessageRepository messageRepository;

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();
		imageRepository.deleteAll();
		requestRepository.deleteAll();
		subscriberRepository.deleteAll();
		messageRepository.deleteAll();

		HashSet<Image> setImages1 = new HashSet<>();
		HashSet<Image> setImages2 = new HashSet<>();
		HashSet<Post> setPosts1 = new HashSet<>();
		HashSet<Post> setPosts2 = new HashSet<>();

		User user = new User("vasya", "abc@ya.ru", "123");
		User user2 = new User("petya", "efg@ya.ru", "456");
		User user3 = new User("sasha", "hij@ya.ru", "789");

		Post post1 = new Post(user, LocalDateTime.now(), "важный первый пост", "История одного поста...");
		Post post2 = new Post(user, LocalDateTime.now(), "второй пост", "Про погоду весной");

		Image image = new Image("Веселый жираф", "jpg", "тут картинка веселого жирафа");
		Image image2 = new Image("Солнце", "jpg", "тут картинка солнца");
		Image image3 = new Image("Снег", "jpg", "тут картинка снега");

		Request request1 = new Request(user, user2, Status.SEND);
		Request request2 = new Request(user, user3, Status.APPROVE);

		Subscriber subscriber1 = new Subscriber(user, user2);
		Subscriber subscriber2 = new Subscriber(user, user3);
		Subscriber subscriber3 = new Subscriber(user3, user);

		Message message = new Message(user, user3, "Это первое сообщение");

		setPosts1.add(post1);
		setPosts2.add(post2);

		setImages1.add(image);
		setImages2.add(image2);
		setImages2.add(image3);

		post1.setImages(setImages1);
		post2.setImages(setImages2);

		userRepository.save(user);
		userRepository.save(user2);
		userRepository.save(user3);

		requestRepository.save(request1);
		requestRepository.save(request2);

		subscriberRepository.save(subscriber1);
		subscriberRepository.save(subscriber2);
		subscriberRepository.save(subscriber3);

		messageRepository.save(message);

		imageRepository.save(image);
		imageRepository.save(image2);
		imageRepository.save(image3);

		postRepository.save(post1);
		postRepository.save(post2);

	}
}
