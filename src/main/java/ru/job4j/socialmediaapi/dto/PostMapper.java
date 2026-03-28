package ru.job4j.socialmediaapi.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.socialmediaapi.dto.*;
import ru.job4j.socialmediaapi.model.*;

import java.util.List;
import java.util.StringTokenizer;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "userId", expression = "java(user.getId())")
    @Mapping(target = "username", expression = "java(user.getName())")
    @Mapping(target = "posts", source = "list")
    PostdDTO getModelFromEntity(User user, List<Post> list);
}
