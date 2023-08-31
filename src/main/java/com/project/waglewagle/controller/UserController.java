package com.project.waglewagle.controller;

import com.project.waglewagle.global.config.jwt.JwtFilter;
import com.project.waglewagle.dto.LoginRequest;
import com.project.waglewagle.dto.ResigetRequest;
import com.project.waglewagle.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/api/v1/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("GG");
    }

    @GetMapping("/api/v1/test2")
    public ResponseEntity<String> test2(){
        return ResponseEntity.ok("GG");
    }
    @PostMapping("/authenticate") // Account 인증 API
    public ResponseEntity<String> authorize(@Valid @RequestBody LoginRequest loginDto) {

        String token = userService.authenticate(loginDto.getEmail(), loginDto.getPassword());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        return new ResponseEntity<>(token, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> signup(
            @Valid @RequestBody ResigetRequest registerDto
    ) {
        userService.signup(registerDto);
        String token = userService.authenticate(registerDto.getEmail(), registerDto.getPassword());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        return new ResponseEntity<>(token, httpHeaders, HttpStatus.OK);
    }

}
