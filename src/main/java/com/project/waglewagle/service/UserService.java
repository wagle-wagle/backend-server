package com.project.waglewagle.service;

import com.project.waglewagle.dto.*;
import com.project.waglewagle.dto.user.LoginResponse;
import com.project.waglewagle.dto.user.UserInfoResponse;
import com.project.waglewagle.entity.MemberType;
import com.project.waglewagle.entity.Users;
import com.project.waglewagle.external.oauth.model.OauthAttributes;
import com.project.waglewagle.global.config.jwt.TokenService;
import com.project.waglewagle.global.error.ErrorCode;
import com.project.waglewagle.global.error.exception.BusinessException;
import com.project.waglewagle.global.error.exception.DuplicateMemberException;
import com.project.waglewagle.global.error.exception.EntityNotFoundException;
import com.project.waglewagle.global.util.EmailService;
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
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;





    // 사용자 인증 및 토큰발급
    @Transactional
    public TokenDto authenticate(Long userId, String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        TokenDto tokenDto = tokenService.createToken(authentication, userId);
        return tokenDto;
    }


    // 로그인
    @Transactional
    public LoginResponse login(String email, String password) {
        Optional<Users> findUser = userRepository.findByEmail(email);

        if(findUser.isEmpty()){
            throw new DuplicateMemberException(ErrorCode.MEMBER_NOT_EXIST);
        }

        if(!passwordEncoder.matches(password, findUser.get().getPassword())){
            throw new DuplicateMemberException(ErrorCode.NOT_MATCHED_PASSWORD);
        }

        TokenDto tokenDto = authenticate(findUser.get().getId(), email, password);

        LoginResponse loginResponse = LoginResponse.builder()
                .userId(findUser.get().getId())
                .userName(findUser.get().getUserName())
                .boardId(findUser.get().getBroad() == null ? null : findUser.get().getBroad().getId())
                .accessToken(tokenDto.getAccessToken())
                .isExistHopae(findUser.get().getUserName() == null ? false : true)
                .build();

        return loginResponse;
    }


    // 회원가입(일반)
    @Transactional
    public UserInfoResponse signup(String email, String password) {
        findMemberByEmail(email);
        Users user = Users.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .memberType(MemberType.GENERAL)
                .roles("ROLE_USER")
                .build();
        Users saveUser = userRepository.save(user);
        return UserInfoResponse.of(saveUser);
    }


    // 회원가입(소셜)
    @Transactional
    public UserInfoResponse signup(OauthAttributes userInfo) {
        Users socialUser = Users.builder()
                .email(userInfo.getEmail())
                .password(passwordEncoder.encode(userInfo.getPassword()))
                .memberType(userInfo.getMemberType())
                .roles("ROLE_USER")
                .build();
        Users saveUser = userRepository.save(socialUser);
        return UserInfoResponse.of(saveUser);
    }



    public Optional<Users> findMemberByEmail(String email){
        Optional<Users> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            throw new DuplicateMemberException(ErrorCode.ALREADY_REGISTERED_MEMBER);
        }
        return user;
    }

    public Optional<Users> findSocialMemberByEmail(String email){
        Optional<Users> user = userRepository.findByEmail(email);
        return user;
    }


    @Transactional
    public UserInfoResponse updateHopae(Long userId, String username){
        Optional<Users> user =  userRepository.findById(userId);
        if(user.isEmpty()){
            throw new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXIST);
        }
        user.get().updateUsername(username);
        UserInfoResponse userResponseDto = UserInfoResponse.of(user.get());
        return userResponseDto;
    }


    public boolean verifyPassword(Long userId, String password){
        Optional<Users> users =  userRepository.findById(userId);
        if(users.isEmpty()){
            throw new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXIST);
        }
        boolean matches = passwordEncoder.matches(password, users.get().getPassword());
        if(!matches){
            throw new BusinessException(ErrorCode.NOT_MATCHED_PASSWORD);
        }
        return matches;
    }


    public boolean verifyEmail(String email){
        Optional<Users> user = userRepository.findByEmail(email);
        boolean isExistEmail = user.isEmpty() ? false : true;
        if(!isExistEmail){
            throw new BusinessException(ErrorCode.EMAIL_NOT_EXIST);
        }
        return isExistEmail;
    }

    @Transactional
    public Users updatePassword(Long userId, String password){
        Optional<Users> users =  userRepository.findById(userId);
        if(users.isEmpty()){
            throw new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXIST);
        }
        users.get().updatePassword(passwordEncoder.encode(password));
        return users.get();
    }


    @Transactional
    public Users deleteUser(Long userId){
        Optional<Users> users =  userRepository.findById(userId);
        if(users.isEmpty()){
            throw new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXIST);
        }
        userRepository.delete(users.get());
        return users.get();
    }

    @Transactional
    public Users sendEmailTemporaryPassword(String email) throws Exception {
        Optional<Users> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new BusinessException(ErrorCode.EMAIL_NOT_EXIST);
        }
        String ePassword = emailService.sendSimpleMessage(email);
        user.get().updatePassword(passwordEncoder.encode(ePassword));
        return user.get();
    }



}
