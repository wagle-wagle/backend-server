package com.project.waglewagle.global.config.security;

import com.project.waglewagle.entity.Users;
import com.project.waglewagle.repository.UserRepository;
import com.project.waglewagle.global.config.security.PrincipalDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        Users users = userRepository.findByEmail(username).get();
        return new PrincipalDetail(users);
    }
}
