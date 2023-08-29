package com.api.shop_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigClass {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();

        // 사용자 페이지 설정
        http.authorizeHttpRequests()
                // 로그인시
                .antMatchers("/member/logout").authenticated()
                // USER, ADMIN
                .antMatchers("/member/**").permitAll()
//                .antMatchers("/member/**").hasAnyRole("ADMIN","USER")
                // ADMIN, SELLER
                .antMatchers("/post/**").hasAnyRole("ADMIN","SELLER")
                // ADMIN
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                // 모두허용
                .anyRequest().permitAll()
        ;

        // 로그인
        http.formLogin()
                .loginPage("/member/login")             //로그인페이지
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/member/login")    // POST
                .defaultSuccessUrl("/")                 // 로그인 후
                .failureUrl("/")
                .permitAll()
                .and()
                .oauth2Login()
                .loginPage("/member/login")
                .userInfoEndpoint()
                .userService(myAuth2UserService())
        ;

        // 로그아웃
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 로그아웃URL
                .logoutSuccessUrl("/")
        ;

        return http.build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> myAuth2UserService(){
        return new MyOauth2UserService();
    }

}
