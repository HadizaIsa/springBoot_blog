package com.blog_assessment.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
@Entity
@Table(name = "users")
@Builder(toBuilder = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

//    @Column(name = "user_type")
//    @Enumerated(EnumType.STRING)
//    private UserTypes user;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_roles",
//    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Set<Role> roles;
}

