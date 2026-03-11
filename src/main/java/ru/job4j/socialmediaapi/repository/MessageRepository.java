package ru.job4j.socialmediaapi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmediaapi.model.Message;
import ru.job4j.socialmediaapi.model.Post;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
