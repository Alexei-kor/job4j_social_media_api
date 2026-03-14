package ru.job4j.socialmediaapi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmediaapi.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByEmailLike(String domen);
}
