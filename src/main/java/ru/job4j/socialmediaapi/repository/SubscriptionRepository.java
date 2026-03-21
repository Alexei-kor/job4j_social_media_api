package ru.job4j.socialmediaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmediaapi.model.Subscription;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long>, JpaRepository<Subscription, Long> {
    @Query(value = """
            SELECT subscriber FROM subscriptions WHERE owner = :ownerId""", nativeQuery = true)
    List<Long> findSubscribersByIDOwner(@Param("ownerId") Long idOwner);

    @Modifying(clearAutomatically = true)
    @Query (value = """
            DELETE FROM subscriptions WHERE owner = :owner_id AND subscriber = :subscriber_id
            """, nativeQuery = true)
    int deleteSubscription(@Param("owner_id") Long ownerId, @Param("subscriber_id") Long subscriberId);
}
