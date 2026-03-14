package ru.job4j.socialmediaapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long>, JpaRepository<Post, Long> {
    List<Post> findByOwner(User user);

    List<Post> findByPeriodBetween(LocalDateTime data1, LocalDateTime data2);

    Page<Post> findByOrderByPeriodDesc(Pageable pageable);
}
