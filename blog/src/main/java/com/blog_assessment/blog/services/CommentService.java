package com.blog_assessment.blog.services;

import com.blog_assessment.blog.entities.Comment;
import com.blog_assessment.blog.entities.Post;
import com.blog_assessment.blog.exceptions.BlogApiException;
import com.blog_assessment.blog.exceptions.EntityNotFoundException;
import com.blog_assessment.blog.repositories.CommentRepository;
import com.blog_assessment.blog.repositories.PostRepository;
import com.blog_assessment.blog.requests.CreateCommentRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Comment createComment(Long postId, CreateCommentRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("post doesn't exist"));

        Comment newComment = buildComment(request);
//        set post to comment entity
        newComment.setPost(post);

        return commentRepository.save(newComment);

    }

    @Transactional
    public Comment getCommentById(Long postId, Long commentId) {
//      retrieve post by id
        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
                .orElseThrow(() -> new EntityNotFoundException("no comment found"));
//        retrieve comment by id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment doesn't exists"));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException("comment does not belong to post");
        }
        return comment;
    }

    @Transactional
    public List<Comment> getAllComments(Long postId) {
        return commentRepository.findAll(postId);
    }

    public Comment updateComment(Long postId, Long commentId,
                                 CreateCommentRequest request) {
        //      retrieve post by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("no comment found"));

        //        retrieve comment by id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment doesn't exists"));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException( "comment doesn't belong to post");
        }
        Comment updatedComment = buildComment(request);

        updatedComment.setPost(post);
        return commentRepository.save(updatedComment);
//        return buildComment(request);
    }


    public void deleteComment(Long postId, Long id) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("post doesnt exist"));

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no comment found"));

        if (comment.getPost().getId().equals(post.getId())){
            commentRepository.deleteById(id);
        }else {
            throw new BlogApiException("comment doesn't exist");
        }
        }


    private Comment buildComment(CreateCommentRequest request){
        return Comment.builder()
            .name(request.getName())
            .email(request.getEmail())
            .body(request.getBody())
            .build();
    }



}
