package ru.job4j.socialmediaapi;

import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.job4j.socialmediaapi.dto.PostMapper;
import ru.job4j.socialmediaapi.dto.PostdDTO;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;

import java.time.LocalDateTime;

@SpringBootApplication
public class SocialMediaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApiApplication.class, args);
	}

}
