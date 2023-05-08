package com.blog_assessment.blog.controllers;

import com.blog_assessment.blog.dtos.UserDto;
import com.blog_assessment.blog.exceptions.BlogApiException;
import com.blog_assessment.blog.mappers.UserDtoMapper;
import com.blog_assessment.blog.requests.LoginRequest;
import com.blog_assessment.blog.requests.SignupRequest;
import com.blog_assessment.blog.services.AuthService;
import com.blog_assessment.blog.services.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    private final UserDtoMapper userDtoMapper;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequest request) {

        try {
            authService.signup(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BlogApiException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);

    }
}
