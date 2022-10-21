package com.ay.study.qna;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 스프링의 환경설정 파일임을 의미
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐어티의 제어를 받도록
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll();
        return http.build();
    }
}
