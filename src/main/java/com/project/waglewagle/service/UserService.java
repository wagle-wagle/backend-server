package com.project.waglewagle.service;

import com.project.waglewagle.dto.ResigetRequest;
import com.project.waglewagle.entity.MemberType;
import com.project.waglewagle.entity.Users;
import com.project.waglewagle.external.oauth.model.OauthAttributes;
import com.project.waglewagle.global.config.jwt.TokenService;
import com.project.waglewagle.global.error.exception.DuplicateMemberException;
import com.project.waglewagle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final TokenService tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createToken(authentication);

        return accessToken;
    }

    @Transactional
    public void signup(ResigetRequest registerDto) {
        if (userRepository.findByEmail(registerDto.getEmail()).orElseGet(()->null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Users user = Users.builder()
                .email(registerDto.getEmail())
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .memberType(MemberType.GENERAL)
                .roles("ROLE_USER")
                .build();
        userRepository.save(user);
    }


    @Transactional
    public Users signup(OauthAttributes userInfo) {
        Users socialUser = Users.builder()
                .email(userInfo.getEmail())
                .username(userInfo.getUsername())
                .password(passwordEncoder.encode(userInfo.getPassword()))
                .memberType(userInfo.getMemberType())
                .roles("ROLE_USER")
                .build();
        return userRepository.save(socialUser);
    }

    public Optional<Users> findMemberByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
