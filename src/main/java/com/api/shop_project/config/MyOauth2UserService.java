package com.api.shop_project.config;

import com.api.shop_project.domain.member.Member;
import com.api.shop_project.domain.member.Role;
import com.api.shop_project.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class MyOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientId = clientRegistration.getClientId();
        String resistrateId = clientRegistration.getRegistrationId();
        String clientName = clientRegistration.getClientName();


        // 실제로 필요한   -> email ,pw, role
        Map<String, Object> attributes = oAuth2User.getAttributes();

        for (String key : attributes.keySet()) {
            System.out.println(key + " : " + attributes.get(key));
        }

        String email = "";
        String password = "dkarjsk";
        String nickName="";

        if (resistrateId.equals("google")) {
            email = (String) attributes.get("email");

        } else if (resistrateId.equals("naver")) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");

            email =(String) response.get("email");
            nickName = (String) response.get("name");

        }else if(resistrateId.equals("kakao")){
            //JSON -> Map 변환
            Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");

            Map<String, Object> profile=(Map<String, Object>) response.get("profile");

            email=(String) response.get("email");
            nickName = (String) profile.get("nickname");
        }

        Optional<Member> optionalMemberEntity = memberRepository.findByEmail(email);
        if (optionalMemberEntity.isPresent()) {
            // oAuthUsr -> DB비교  있으면
            return new MyUserDetails(optionalMemberEntity.get());
        }

        Member member = memberRepository.save(Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(nickName)                                // name 널이라 나중에 바꿔야함
                .role(Role.USER)
                .build());

        // Dto 저장 후 컨트롤러 보내서 추가 입력 페이지 이동 -> 재 가입
//        MemberDto memberDto = MemberDto.builder()
//                .email(email)
//                .name(email)
//                .password(passwordEncoder.encode(password))
//                .role(Role.USER)
//                .build();
//
//        MemberController

        // oAuthUsr -> DB비교  없으면 생성
        return new MyUserDetails(member, attributes);
    }



}