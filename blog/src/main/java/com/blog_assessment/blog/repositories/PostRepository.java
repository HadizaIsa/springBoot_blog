package com.blog_assessment.blog.repositories;

import com.blog_assessment.blog.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Override
    @Query("SELECT p FROM Post p")
    Page<Post> findAll(Pageable pageable);
}
