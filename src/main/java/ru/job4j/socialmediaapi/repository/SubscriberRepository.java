package ru.job4j.socialmediaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Subscriber;

import java.util.List;

public interface SubscriberRepository extends CrudRepository<Subscriber, Long>, JpaRepository<Subscriber, Long> {
    @Query(value = """
            SELECT subscribers_id FROM users_subscribers 
            WHERE user_id = :ownerId""", nativeQuery = true)
    List<Long> findSubscribersByIDOwner(@Param("ownerId") Long idOwner);
}
