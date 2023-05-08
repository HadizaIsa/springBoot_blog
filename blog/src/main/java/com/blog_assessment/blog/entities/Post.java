package com.blog_assessment.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(min = 2, message = "title should not be less than 2 characters")
    private String title;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size( min= 10, message = "description should contain at least 10 characters")
    private String description;

    @Column(name = "content", nullable = false)
    @NotBlank
    private String content;

    private LocalDateTime createdAt;

//    @ManyToOne
//    @JoinColumn(name = "author_id")
//    private Author author;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    @Column
    @NotBlank
    private String username;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();
}
