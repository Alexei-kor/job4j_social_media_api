package ru.job4j.socialmediaapi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Request;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
