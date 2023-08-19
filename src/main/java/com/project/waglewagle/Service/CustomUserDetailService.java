package com.project.waglewagle.Service;

import com.project.waglewagle.Entity.Users;
import com.project.waglewagle.Repository.UserRepository;
import com.project.waglewagle.auth.PrincipalDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
