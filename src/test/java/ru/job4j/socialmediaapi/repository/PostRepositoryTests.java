package ru.job4j.socialmediaapi.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.Subscription;
import ru.job4j.socialmediaapi.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@AfterEach
	public void setUp() {
		imageRepository.deleteAll();
		postRepository.deleteAll();
		subscriptionRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	public void whenSavePostthenFindById() {
		User user = new User("vasya", "abc@ya.ru", "123");
		Post post1 = new Post(user, LocalDateTime.now(), "важный первый пост", "История одного поста...");
		userRepository.save(user);
		postRepository.save(post1);
		var foundUser = userRepository.findById(user.getId());
		assertThat(foundUser).isPresent();
		assertThat(foundUser.get().getName()).isEqualTo("vasya");
	}

	@Test
	public void whenFindAllthenReturnAllPersons() {
		User user = new User("vasya1", "abc@ya.ru", "123");
		Post post1 = new Post(user, LocalDateTime.now(), "важный первый пост", "История одного поста...");
		Post post2 = new Post(user, LocalDateTime.now(), "Второй пост", "Про погоду");
		userRepository.save(user);
		postRepository.save(post1);
		postRepository.save(post2);
		var a = postRepository.findAll();
		assertThat(a).hasSize(2);
	}

	@Test
	public void whenFindPostsByUser() {
		User userVasya = new User("vasya", "vasya@ya.ru", "123");
		User userSasha = new User("sasha", "sasha@ya.ru", "123");
		Post post1 = new Post(userSasha, LocalDateTime.of(2026, 3, 1, 0, 0), "первый пост", "Про авто");
		Post post2 = new Post(userSasha, LocalDateTime.of(2026, 2, 1, 0, 0), "второй пост", "Про зверей");
		Post post3 = new Post(userVasya, LocalDateTime.of(2026, 1, 1, 0, 0), "третий пост", "Про погоду");
		userRepository.saveAll(List.of(userSasha, userVasya));
		postRepository.saveAll(List.of(post1, post2, post3));
		List<Post> findPostsVasya = postRepository.findByOwner(userVasya);
		List<Post> findPostsSasha = postRepository.findByOwner(userSasha);
		assertThat(findPostsVasya).hasSize(1);
		assertThat(findPostsSasha).hasSize(2);
		org.junit.jupiter.api.Assertions.assertEquals(findPostsVasya.size(), 1);
	}

	@Test
	public void whenFindPostsByPeriod() {
		User userVasya = new User("vasya", "vasya@ya.ru", "123");
		User userSasha = new User("sasha", "sasha@ya.ru", "123");
		Post post1 = new Post(userSasha, LocalDateTime.of(2026, 3, 1, 0, 0), "первый пост", "Про авто");
		Post post2 = new Post(userSasha, LocalDateTime.of(2026, 2, 1, 0, 0), "второй пост", "Про зверей");
		Post post3 = new Post(userVasya, LocalDateTime.of(2026, 1, 1, 0, 0), "третий пост", "Про погоду");
		userRepository.saveAll(List.of(userSasha, userVasya));
		postRepository.saveAll(List.of(post1, post2, post3));
		LocalDateTime data1 = LocalDateTime.parse("2026-01-01T00:00:00");
		LocalDateTime data2 = LocalDateTime.parse("2026-02-28T00:00:00");
		List<Post> findPosts = postRepository.findByPeriodBetween(data1, data2);
		assertThat(findPosts).hasSize(2);
	}

	@Test
	public void whenFindPostsByPeriodWithPage() {
		User userVasya = new User("vasya", "vasya@ya.ru", "123");
		User userSasha = new User("sasha", "sasha@ya.ru", "123");
		Post post1 = new Post(userSasha, LocalDateTime.of(2026, 3, 1, 0, 0), "первый пост", "Про авто");
		Post post2 = new Post(userSasha, LocalDateTime.of(2026, 2, 1, 0, 0), "второй пост", "Про зверей");
		Post post3 = new Post(userVasya, LocalDateTime.of(2026, 1, 1, 0, 0), "третий пост", "Про погоду");
		userRepository.saveAll(List.of(userSasha, userVasya));
		postRepository.saveAll(List.of(post1, post2, post3));

		Page<Post> findPosts = postRepository.findByOrderByPeriodDesc(org.springframework.data.domain.Pageable.ofSize(2));
		assertThat(findPosts.getTotalPages() == 2);
	}

	@Test
	@Transactional
	public void whenUpdateHeadAndTextPost() {
		User userVasya = new User("vasya", "vasya@ya.ru", "123");
		User userSasha = new User("sasha", "sasha@ya.ru", "123");
		Post post1 = new Post(userSasha, LocalDateTime.of(2026, 3, 1, 0, 0), "первый пост", "Про авто");
		Post post2 = new Post(userSasha, LocalDateTime.of(2026, 2, 1, 0, 0), "второй пост", "Про зверей");
		Post post3 = new Post(userVasya, LocalDateTime.of(2026, 1, 1, 0, 0), "третий пост", "Про погоду");
		userRepository.saveAll(List.of(userSasha, userVasya));
		postRepository.saveAll(List.of(post1, post2, post3));

		int coundUpdating = postRepository.updateHeadAndTextPost(post2.getId(), "Измененный второй пост", "Измененный текст поста");
		Assertions.assertThat(coundUpdating).isEqualTo(1);

		Optional<Post> foundPosts = postRepository.findById(post2.getId());
		Assertions.assertThat(foundPosts.get().getHead()).isEqualTo("Измененный второй пост");
	}

	@Test
	@Transactional
	public void whenDeleteImagePost() {
		User userVasya = new User("vasya", "vasya@ya.ru", "123");
		Post post1 = new Post(userVasya, LocalDateTime.of(2026, 3, 1, 0, 0), "первый пост", "Про авто");
		Image image = new Image("Веселый жираф", "jpg", "тут картинка веселого жирафа");
		Image image2 = new Image("Солнце", "jpg", "тут картинка солнца");
		Image image3 = new Image("Снег", "jpg", "тут картинка снега");

		HashSet<Image> setImages = new HashSet<>();
		setImages.add(image);
		setImages.add(image2);
		setImages.add(image3);

		post1.setImages(setImages);

		imageRepository.saveAll(List.of(image, image2, image3));
		userRepository.saveAll(List.of(userVasya));
		postRepository.saveAll(List.of(post1));

		int countDeleteImg = postRepository.deleteImagePost(post1.getId(), image.getId());
		Assertions.assertThat(countDeleteImg).isEqualTo(1);

		List<Long> count = postRepository.findImageByIDPost(post1.getId());
		assertThat(count.size()).isEqualTo(2);
	}

	@Test
	@Transactional
	public void whenDeletePost() {
		User userVasya = new User("vasya", "vasya@ya.ru", "123");
		Post post1 = new Post(userVasya, LocalDateTime.of(2026, 3, 1, 0, 0), "первый пост", "Про авто");
		Post post2 = new Post(userVasya, LocalDateTime.of(2026, 2, 1, 0, 0), "второй пост", "Про зверей");
		userRepository.saveAll(List.of(userVasya));
		postRepository.saveAll(List.of(post1, post2));

		int countDel = postRepository.deletePost(post1.getId());
		Assertions.assertThat(countDel).isEqualTo(1);

		Optional<Post> foundPosts = postRepository.findById(post1.getId());
		Assertions.assertThat(foundPosts.isPresent()).isEqualTo(false);
	}

	@Test
	public void whenFindPostsBySubscriber() {

		subscriptionRepository.deleteAll();
		userRepository.deleteAll();

		User user1 = new User("vasya", "abc@ya.ru", "123");
		User user2 = new User("petya", "cde@ya.ru", "123");
		User user3 = new User("sasha", "abc@mail.ru", "123");
		User user4 = new User("misha", "abc@mail.ru", "123");

		userRepository.saveAll(List.of(user1, user2, user3, user4));

		Subscription subscription1 = new Subscription(user1, user4);
		Subscription subscription2 = new Subscription(user1, user2);
		Subscription subscription3 = new Subscription(user2, user3);

		subscriptionRepository.saveAll(List.of(subscription1, subscription2, subscription3));

		List<Long> foundSub = subscriptionRepository.findSubscribersByIDOwner(user1.getId());
		List<User> listUsers = new ArrayList<>();
		for (Long cur : foundSub) {
			Optional<User> optional = userRepository.findById(cur);
			if (optional.isPresent()) {
				listUsers.add(optional.get());
			}
		}

		Post post1 = new Post(user2, LocalDateTime.of(2026, 3, 1, 0, 0), "первый пост", "Про авто");
		Post post2 = new Post(user4, LocalDateTime.of(2026, 2, 1, 0, 0), "второй пост", "Про зверей");
		Post post3 = new Post(user1, LocalDateTime.of(2026, 1, 1, 0, 0), "третий пост", "Про погоду");

		postRepository.saveAll(List.of(post1, post2, post3));

		Page<Post> findPosts = postRepository.findByOwnerInOrderByPeriodDesc(listUsers, org.springframework.data.domain.Pageable.ofSize(2));
		assertThat(findPosts.getTotalPages() == 1);

	}

}
