package ru.job4j.socialmediaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmediaapi.model.Subscription;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long>, JpaRepository<Subscription, Long> {
    @Query(value = """
            SELECT subscriber FROM subscriptions WHERE owner = :ownerId""", nativeQuery = true)
    List<Long> findSubscribersByIDOwner(@Param("ownerId") Long idOwner);
}
