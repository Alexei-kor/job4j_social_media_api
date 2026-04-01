package ru.job4j.socialmediaapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmediaapi.model.Image;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long>, JpaRepository<Post, Long> {
    List<Post> findByOwner(User user);

    List<Post> findByPeriodBetween(LocalDateTime data1, LocalDateTime data2);

    Page<Post> findByOrderByPeriodDesc(Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query (value = """
            UPDATE Post post SET post.head = :newHead, post.text = :newText
            WHERE post.id = :id""")
    int updateHeadAndTextPost(@Param("id") Long id, @Param("newHead") String newHead, @Param("newText") String newText);

    @Modifying(clearAutomatically = true)
    @Query (value = """
            DELETE FROM images WHERE post_id = :idPost AND id = :idImage
            """, nativeQuery = true)
    int deleteImagePost(@Param("idPost") Long idPost, @Param("idImage") Long idImage);

    @Query (value = """
            SELECT id FROM images WHERE post_id = :idPost
            """, nativeQuery = true)
    List<Long> findImageByIDPost(@Param("idPost") Long idPost);

    @Modifying(clearAutomatically = true)
    @Query (value = """
            DELETE FROM posts WHERE id = :idPost
            """, nativeQuery = true)
    int deletePost(@Param("idPost") Long idPost);

    Page<Post> findByOwnerInOrderByPeriodDesc(List<User> idS, Pageable pageable);

    @Query (value = """
            SELECT post FROM Post post WHERE post.owner.id in :listID ORDER BY post.id DESC""")
    List<Post> findByListOwnerIDsOrderByPeriodDesc(@Param("listID") List<Long> listID);

}
