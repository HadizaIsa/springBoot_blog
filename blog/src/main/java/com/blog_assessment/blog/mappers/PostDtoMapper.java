package com.blog_assessment.blog.mappers;

import com.blog_assessment.blog.dtos.CommentDto;
import com.blog_assessment.blog.dtos.PostDto;
import com.blog_assessment.blog.entities.Comment;
import com.blog_assessment.blog.entities.Post;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class PostDtoMapper implements EntityMapper<Post, PostDto>{

    private final CommentDtoMapper commentDtoMapper;
    @Override
    public List<PostDto> mapToDtos(List<Post> posts) {
        return posts
            .stream()
            .map(this::mapToDto)
            .collect(toList());
    }

    @Override
    public List<Post> mapToEntities(List<PostDto> postDtos) {
        return postDtos
            .stream()
            .map(this::mapToEntity)
            .collect(toList());
    }

    @Override
    public PostDto mapToDto(Post post) {
        if(post == null){
            return null;
        }
        Set<CommentDto> comments = new HashSet<>();
        for (Comment comment : post.getComments()) {
            CommentDto commentDto = CommentDto.builder()
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody())
                .build();
            comments.add(commentDto);
        }
        return PostDto.builder()
            .title(post.getTitle())
            .description(post.getDescription())
            .content(post.getContent())
            .comments(comments)
            .build();
    }

    @Override
    public Post mapToEntity(PostDto postDto) {
        if (postDto == null) {
            return null;
        }
        Set<Comment> comments = new HashSet<>();
        for (CommentDto commentDto : postDto.getComments()) {
            Comment comment = Comment.builder()
                    .name(commentDto.getName())
                    .email(commentDto.getEmail())
                    .body(commentDto.getBody()) // map the message field to the body field
                    .build();
            comments.add(comment);
        }
            return Post.builder()
                    .title(postDto.getTitle())
                    .description(postDto.getDescription())
                    .content(postDto.getContent())
                    .comments(comments)
                    .build();
        }

    public Page<PostDto> mapToPagedDto(Page<Post> posts){
        return new PageImpl<>(
            mapToDtos(posts.getContent()),
            posts.getPageable(),
            posts.getTotalElements()
        );
    }

}
