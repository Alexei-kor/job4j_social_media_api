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
		userRepository.deleteAll();
	}

	@Test
	public void whenSaveUser_thenFindById() {
		User user = new User("vasya", "abc@ya.ru", "123");
		userRepository.save(user);
		var foundUser = userRepository.findById(user.getId());
		assertThat(foundUser).isPresent();
		assertThat(foundUser.get().getName()).isEqualTo("vasya");
	}

	/*@Test
	public void whenFindAll_thenReturnAllPersons() {
		var person1 = new Person();
		person1.setName("John Doe");
		person1.setEmail("john.doe@example.com");
		var person2 = new Person();
		person2.setName("Jane Doe");
		person2.setEmail("jane.doe@example.com");
		personRepository.save(person1);
		personRepository.save(person2);
		var persons = personRepository.findAll();
		assertThat(persons).hasSize(2);
		assertThat(persons).extracting(Person::getName).contains("John Doe", "Jane Doe");
	}*/

}
