package com.blog_assessment.blog.mappers;

import com.blog_assessment.blog.dtos.CommentDto;
import com.blog_assessment.blog.entities.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class CommentDtoMapper implements EntityMapper<Comment, CommentDto>{

    @Override
    public List<CommentDto> mapToDtos(List<Comment> comments) {
        return comments
            .stream()
            .map(this::mapToDto)
            .collect(toList());
    }

    @Override
    public List<Comment> mapToEntities(List<CommentDto> commentDtos) {
        return commentDtos
            .stream()
            .map(this::mapToEntity)
            .collect(toList());
    }

    @Override
    public CommentDto mapToDto(Comment comment) {
        if(comment == null) {
            return null;
        }
        return CommentDto.builder()
            .name(comment.getName())
            .email(comment.getEmail())
            .body(comment.getBody())
            .build();
    }

    @Override
    public Comment mapToEntity(CommentDto commentDto) {
        if(commentDto == null) {
            return null;
        }
        return Comment.builder()
            .name(commentDto.getName())
            .email(commentDto.getEmail())
            .body(commentDto.getBody())
            .build();
    }
}
