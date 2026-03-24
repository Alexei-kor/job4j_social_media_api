package ru.job4j.socialmediaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {
    List<User> findByEmailLike(String domen);

    @Query ("""
            select user from User as user where user.name = :login and user.password = :pass
            """)
    Optional<User> findByLoginAndPass(@Param("login") String login, @Param("pass") String pass);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE User u SET u.name = :#{#user.name}, u.email = :#{#user.email}, u.password = :#{#user.password} WHERE u.id = :#{#user.id}""")
    int update(@Param("user") User user);

    @Modifying
    @Query("delete from User user where user.id=:Id")
    int delete(@Param("Id") Long id);

}
