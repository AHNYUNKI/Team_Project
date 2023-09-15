package com.api.shop_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
                .defaultSuccessUrl("/index")                 // 로그인 후
                .failureUrl("/member/login")
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
        
//        // 세션 설정
//        http.sessionManagement(
//                // sessionCreationPolicy는 세션의 생성 정책 , 보통 ALWAYS (항상 생성) , STATELESS (모든 요청에 대해 세션을 생성하지 않음) 두가지로 설정
//                s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                        // sessionFixation() : 세션의 생성 방식, changeSessionId() : 인증마다 새로운 세션아이디 발급 . 다른 세션 속성은 유지
//                        .sessionFixation(sf ->sf.changeSessionId())
//        // 동시 접속 문제 해결를 위한 속성
//                        // maximumSession() : 유저가 갖을 수 있는 세션의 갯수
//                        .maximumSessions(1)
//                        // maxSessionsPreventsLogin() : 사용자 수를 초과시 상황을 정의
//                        // true 일 경우 초과된 사용자에게 로그인을 허용, false 일 경우 초과된 사용자에게 로그인을 허용하지 않음. 초과된 사용자는 로그인 할 수 없게 된다.
//                        .maxSessionsPreventsLogin(true)
//                        // expiredUrl() : 세션 만료 시 보낼 페이지
//                        .expiredUrl("/sessionExpired"));



        return http.build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> myAuth2UserService(){
        return new MyOauth2UserService();
    }

}