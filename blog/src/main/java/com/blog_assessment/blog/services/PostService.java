package com.blog_assessment.blog.services;

import com.blog_assessment.blog.entities.Post;
import com.blog_assessment.blog.exceptions.EntityNotFoundException;
import com.blog_assessment.blog.repositories.PostRepository;
import com.blog_assessment.blog.requests.CreatePostRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final AuthService authService;

    public Post createPost(CreatePostRequest request){
        Post newPost = buildPost(request);
        return postRepository.save(newPost);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Page<Post> getAllPosts(Pageable pageable){
        return postRepository.findAll(pageable);
    }
    public Post getPostById(Long id){
        return postRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Post doesn't exist"));
    }

    public Post updatePost(CreatePostRequest request, Long id){

        return postRepository.save(buildPost(request));
    }

    public void deleteById(Long id){
        postRepository.deleteById(id);
    }

    private Post buildPost(CreatePostRequest request){
        User loggedInUser = authService.getCurrentUser()
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        Post post = Post.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .content(request.getContent())
            .build();

        post.setCreatedAt(LocalDateTime.now());
        post.setUsername(loggedInUser.getUsername());

        return post;
    }
}
