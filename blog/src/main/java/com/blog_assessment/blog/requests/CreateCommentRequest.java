package com.blog_assessment.blog.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateCommentRequest {
    private String name;

    private String email;

    private String body;
}
