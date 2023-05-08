package com.blog_assessment.blog.services;

import com.blog_assessment.blog.entities.User;
import com.blog_assessment.blog.exceptions.BlogApiException;
import com.blog_assessment.blog.repositories.UserRepository;
import com.blog_assessment.blog.requests.LoginRequest;
import com.blog_assessment.blog.requests.SignupRequest;
import com.blog_assessment.blog.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    public AuthenticationResponse login(LoginRequest loginRequest){

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());

    }

    public void signup(SignupRequest request){
//        add check for email exists in database
        if(userRepository.existsByEmail(request.getEmail())){
            throw new BlogApiException("email already exists!");
        }
//        add check for username exists in database
        if(userRepository.existsByUsername(request.getUsername())){
            throw new BlogApiException("username already exists!");
        }

        User newUser = buildNewUser(request);
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

//        Set<Role> roles = new HashSet<>();
//
//        Role userRole = roleRepository.findByName("ROLE_USER").get();
//        roles.add(userRole);
//
//        newUser.setRoles(roles);
        userRepository.save(newUser);
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }


    private User buildNewUser(SignupRequest request){
        return User.builder()
            .name(request.getName())
            .username(request.getUsername())
            .email(request.getEmail())
            .password(request.getPassword())
            .build();
    }
}
