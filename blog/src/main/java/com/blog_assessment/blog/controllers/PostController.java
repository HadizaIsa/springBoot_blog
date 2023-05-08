package com.blog_assessment.blog.controllers;

import com.blog_assessment.blog.dtos.PostDto;
import com.blog_assessment.blog.mappers.PostDtoMapper;
import com.blog_assessment.blog.requests.CreatePostRequest;
import com.blog_assessment.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    private final PostDtoMapper postDtoMapper;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody CreatePostRequest request){
        return new ResponseEntity<>(postDtoMapper.mapToDto(postService.createPost(request)), HttpStatus.CREATED);
    }

//    @PostMapping
//    public ResponseEntity createPost(@Valid @RequestBody CreatePostRequest request){
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(postDtoMapper.mapToDtos(postService.getAllPosts()));
    }

//    @GetMapping("/paged")
//    public ResponseEntity<Page<PostDto>> getAllPosts(@Valid PageParam pageParam){
//
//        return ResponseEntity.ok(postDtoMapper.mapToPagedDto(
//            postService.getAllPosts(
//                PageRequest.of(
//                    pageParam.getOffset(),
//                    pageParam.getLimit(),
//                    Sort.by("id").descending()))
//        ));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(
                postDtoMapper.mapToDto(postService.getPostById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody CreatePostRequest request, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok(
                postDtoMapper.mapToDto(postService.updatePost(request, id))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long id){
        postService.deleteById(id);

        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }
}
