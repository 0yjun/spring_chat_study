package com.chatflatform.security;

import com.chatflatform.domain.auth.model.CustomUserDetails;
import com.chatflatform.domain.auth.service.CustomUserDetailsService;
import com.chatflatform.domain.user.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

//    public JwtRequestFilter(JWTUtil jwtUtil,CustomUserDetailsService customUserDetailsService) {
//        this.jwtUtil = jwtUtil;
//        this.customUserDetailsService=customUserDetailsService;
//    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain
    ) throws ServletException, IOException {
        String authorization= request.getHeader("Authorization");
        // 회원가입이나 로그인 요청인지 확인
        if (request.getRequestURI().startsWith("/auth/")) {
            filterChain.doFilter(request, response); // 인증 없이 다음 필터로 진행
            return; // 필터 종료
        }

        // Authorization 헤더가 "Bearer "로 시작하는지 확인
        if(!StringUtils.hasText(authorization) || !authorization.startsWith("Bearer ")){
            //TODO error
            log.error("token is null");
            filterChain.doFilter(request,response);
            return;
        }
        String token = authorization.split(" ")[1];


//        if(jwtUtil.isExpired(token)){
//            //TODO error
//            System.out.println("token expired");
//            filterChain.doFilter(request, response);
//            return;
//        }
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        User user = User.builder()
                .name(username)
                .build();
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        Authentication authentication
                = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
