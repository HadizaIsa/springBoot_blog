package com.blog_assessment.blog.mappers;

import com.blog_assessment.blog.dtos.UserDto;
import com.blog_assessment.blog.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserDtoMapper implements EntityMapper<User, UserDto> {

    @Override
    public List<UserDto> mapToDtos(List<User> users) {
        return users
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<User> mapToEntities(List<UserDto> userDtos) {
        return userDtos
            .stream()
            .map(this::mapToEntity)
            .collect(Collectors.toList());
    }

    @Override
    public UserDto mapToDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
            .name(user.getName())
            .username(user.getUsername())
            .email(user.getEmail())
            .password(user.getPassword())
            .build();
    }

    @Override
    public User mapToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return User.builder()
            .name(userDto.getName())
            .username(userDto.getUsername())
            .email(userDto.getEmail())
            .password(userDto.getPassword())
            .build();
    }
}

