package ru.job4j.socialmediaapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.socialmediaapi.dto.PostdDTO;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.service.PostServiceDB;

import java.util.List;

@Validated
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/post")
@Tag(name = "PostController", description = "PostController management APIs")
public class PostController {

    private PostServiceDB postServiceDB;

    @GetMapping("/{postId}")
    public ResponseEntity<Post> get(@PathVariable("postId")
                                    @NotNull
                                    @Min(value = 1, message = "номер ресурса должен быть 1 и более")
                                    Long postId) {
        return postServiceDB.get(postId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{ownerId}")
    public ResponseEntity<Post> save(@PathVariable("ownerId")
                                         @NotNull
                                         @Min(value = 1, message = "номер ресурса должен быть 1 и более")
                                         Long ownerId,
                                     @RequestBody Post post) {
        postServiceDB.create(ownerId, post);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(post);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Post post) {
        if (postServiceDB.update(post)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> removeById(@PathVariable long postId) {
        if (postServiceDB.delete(postId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<List<PostdDTO>> getPostsByUsersIDs(@RequestParam("listIDs")
                                                                 @NotNull
                                                                 @Size(min = 1, message = "Должен быть указан хотя бы один user ID")
                                                                 List<Long> listIDs) {
        List<PostdDTO> list = postServiceDB.findByOwnerInOrderByPeriodDesc(listIDs);
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }
}
