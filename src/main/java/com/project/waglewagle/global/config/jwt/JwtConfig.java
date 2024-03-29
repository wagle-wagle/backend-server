package com.project.waglewagle.global.config.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {

    // 액세스 토큰 발급용, 리프레시 토큰 발급용은 각각 별도의 키와 유효기간을 갖는다.
    @Bean(name = "tokenService")
    public TokenService tokenService(JwtProperties jwtProperties) {
        return new TokenService(jwtProperties.getSecret(), jwtProperties.getAccessTokenValidityInSeconds());
    }
}