package ru.job4j.socialmediaapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmediaapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByEmailLike(String domen);

    @Query ("""
            select user from User as user where user.name = :login and user.password = :pass
            """)
    Optional<User> findByLoginAndPass(@Param("login") String login, @Param("pass") String pass);

}
