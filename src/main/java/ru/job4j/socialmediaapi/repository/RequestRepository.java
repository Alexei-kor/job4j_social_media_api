package ru.job4j.socialmediaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Request;
import ru.job4j.socialmediaapi.model.Status;
import ru.job4j.socialmediaapi.model.User;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long>, JpaRepository<Request, Long> {
    @Query(value = """
            SELECT friend FROM requests WHERE from_id = :from_id AND status = :status""", nativeQuery = true)
    List<Long> findFriendsByIDOwner(@Param("from_id") Long fromId, @Param("status") Status status);

    @Modifying(clearAutomatically = true)
    @Query (value = """
            UPDATE Request request SET request.status = :newStatus WHERE request.from = :from AND request.friend = :friend""")
    int updateStatusRequest(@Param("from") User from, @Param("friend") User friend, @Param("newStatus") Status status);

}
