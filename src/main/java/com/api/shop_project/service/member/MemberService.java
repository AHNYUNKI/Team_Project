package com.api.shop_project.service.member;

import com.api.shop_project.domain.member.Address;
import com.api.shop_project.domain.member.Member;
import com.api.shop_project.domain.member.Role;
import com.api.shop_project.dto.response.member.MemberDto;
import com.api.shop_project.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

//    @Transactional
//    public void insertMember(String email, String password, String name) {
//
//        Long memberId = memberRepository.save(Member.builder()
//                .email(email)
//                .password(passwordEncoder.encode(password))
//                .name(name)
//                        .address(Address.builder()
//                                .City("602호").build())
//                .role(Role.USER)
//                .build()).getId();
//
//        Member member1
//                = memberRepository.findById(memberId).orElseThrow(() -> {
//            throw new IllegalArgumentException("아이디가 없음.");
//        });
//    }

    @Transactional
    public void memberInsert(MemberDto memberdto) {

        Long memberId = memberRepository.save(Member.builder()
                .email(memberdto.getEmail())
                .password(passwordEncoder.encode(memberdto.getPassword()))
                .name(memberdto.getName())
                .address(Address.builder()
                        .City(memberdto.getAddress().getCity())
                        .street(memberdto.getAddress().getStreet())
                        .zipcode(memberdto.getAddress().getZipcode())
                        .build())
                .role(Role.USER)
                .build()).getId();

        Member member1
                = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalArgumentException("아이디가 없음.");
        });
    }

//        memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

//        Member memberEntity
//                = Member.toMemberEntity(memberDto, passwordEncoder);
//
//        Long memberId = memberRepository.save(memberEntity).getId();
//
//        Member memberEntity1
//                = memberRepository.findById(memberId).orElseThrow(() -> {
//            throw new IllegalArgumentException("아이디가 없습니다.");
//        });


        public int memberDelete(Long id) {

//    memberRepository.findById(id).orElseThrow(()->{
//      return new IllegalArgumentException("삭제할 아이디가 없습니다.");
//    });
//    Member member=  Member.builder()
//            .id(id)
//            .build();

            Optional<Member> optionalMember=
                    Optional.ofNullable(memberRepository.findById(id).orElseThrow(() -> {
                        return new IllegalArgumentException("삭제할 아이디 없음");
                    }));

            memberRepository.delete(optionalMember.get()); // 해당 id 삭제

//    memberRepository.deleteId(optionalBoardEntity.get().getId());
            Optional<Member> optionalMember1
                    =  memberRepository.findById(id); // 해당 id 조회
            if(!optionalMember1.isPresent()){
                // 삭제 정상 실행
                return 1;
            }
            return 0;

        }

    @Transactional
    public int memberUpdate(MemberDto memberDto) {

//        // MemberEntity id확인
//        Optional<Member>  optionalMemberEntity=
//                Optional.ofNullable(memberRepository.findById(memberDto.getId()).orElseThrow(() -> {
//                    return new IllegalArgumentException("수정할 아이디가 없습니다.");
//                }));

        Member memberEntity=
                Member.builder()
                        .id(memberDto.getId())
                        .name(memberDto.getName())
                        .email(memberDto.getEmail())
                        .password(passwordEncoder.encode(memberDto.getPassword()))
                        .phone(memberDto.getPhone())
                        .address(memberDto.getAddress())
                        .role(Role.USER)
                        .build();


        Long memberId= memberRepository.save(memberEntity).getId();

        Optional<Member>  optionalMemberEntity2=
                Optional.ofNullable(memberRepository.findById(memberId).orElseThrow(() -> {
                    return new IllegalArgumentException("수정 아이디가 없습니다.");
                }));

        if(optionalMemberEntity2.isPresent()){
            // 수정이 정상 실행
            return 1;
        }
        return 0;
    }

    @Transactional
    public MemberDto memberUpdateOk(Long id) {
        // MemberEntity id 확인
        Optional<Member> optionalMemberEntity =
                Optional.ofNullable(memberRepository.findById(id).orElseThrow(()->{
                    return new IllegalArgumentException("수정할 아이디가 없습니다.");
                }));

        Member memberEntity = optionalMemberEntity.get();

        if (optionalMemberEntity.isPresent()){
            // Entity-> Dto
            MemberDto memberDto = MemberDto.builder()
                    .id(memberEntity.getId())
                    .name(memberEntity.getName())
                    .email(memberEntity.getEmail())
                    .password(passwordEncoder.encode(memberEntity.getPassword()))
                    .phone(memberEntity.getPhone())
                    .address(memberEntity.getAddress())
                    .role(Role.USER)
                    .build();
            return memberDto;
        }

        return null;
    }




    private int addressDiv(String address) {
        int rs=0;
        return rs;
    }



}
