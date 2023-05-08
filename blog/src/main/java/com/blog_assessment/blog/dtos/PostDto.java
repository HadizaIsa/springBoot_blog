package com.blog_assessment.blog.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PostDto {

    private String title;

    private String description;

    private String content;

    private String name;

//    private Author author;

    private Set<CommentDto> comments;
}
