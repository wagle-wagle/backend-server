package com.project.waglewagle.Service;

import com.project.waglewagle.Entity.Users;
import com.project.waglewagle.Repository.UserRepository;
import com.project.waglewagle.auth.PrincipalDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(username);
        return new PrincipalDetail(users);
    }
}
