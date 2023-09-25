package com.project.waglewagle.global.error.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.waglewagle.dto.common.CommonResponse;
import com.project.waglewagle.global.error.ErrorCode;
import com.project.waglewagle.global.util.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ExceptionFilterHandler extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            setErrorResponse(response, ErrorCode.NOT_VALID_TOKEN);
        } catch (AccessDeniedException e) {
            setErrorResponse(response, ErrorCode.NOT_ACCESS_AUTHORITY);
        } catch (JwtException e) {
            filterChain.doFilter(request, response);
        }
    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) {

        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        CommonResponse commonResponseException = ApiResponse.createFail(errorCode.getHttpStatus(), errorCode.getMessage());

        try {
            response.getWriter().write(objectMapper.writeValueAsString(commonResponseException));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
