package ru.job4j.socialmediaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.job4j.socialmediaapi.model.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostdDTO {

    private Long userId;

    private String username;

    List<Post> posts = new ArrayList<>();
}
