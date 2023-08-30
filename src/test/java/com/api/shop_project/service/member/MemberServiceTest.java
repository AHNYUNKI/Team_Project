package com.api.shop_project.service.member;

import com.api.shop_project.domain.member.Address;
import com.api.shop_project.dto.response.member.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;



    @Test
    public void 회원_수정() {

        // Test로는 ok 웹 페이지에서 값 넘어오는 문제 setter
        // 수정시 기본 주소가 없으면 오류

        MemberDto memberDto = MemberDto.builder()
                .id(3L)
                .name("m4수")
                .email("m4수@gmail.com")
                .password("4444")
                .build();

        memberService.memberUpdate(memberDto);

//        memberService.memberUpdateOk(1L);

//        memberService.update(1L, "3333", "user18");
//        memberService.updatMember(1L, "3333", "user3", "m2@gmail.com","서울특별시 그린 602호");
    }
    @Test
    public void 회원_가입() {
        MemberDto memberDto = MemberDto.builder()
                .email("AAAa32323333aabb@qwe")
                .name("이름")
                .password("1111")
                .phone("010-1111-1111")
                .address(Address.builder()
                        .City("")
                        .street("")
                        .build())
                .build();

        memberService.memberInsert(memberDto);

    }

    @Test
    public void 회원_삭제() {
        memberService.memberDelete(1L); // 해당 id 삭제
    }

    @Test
    public int addressDiv(String address){
//        String address = "서울특별시 노원구 상계로3길 21 화일빌딩 3층 6층";
        System.out.println(address);

        String[] arr = address.split("");

        int i = 0;
        int citycheck = 0;

        String addException = "시흥시, 군위군, 군포시, 군산시, 구미시, 구례군, 양구군, 구리시, 구로구, 양주시";

        String[] arr2 = addException.split(", ");

        for(String str : arr2){
            if(address.indexOf(str)!=-1){
                if((address.substring(address.indexOf(str)+3,address.indexOf(str)+4)).equals(" ")){
                    System.out.println(address.substring(address.indexOf(str)+4,address.length()));	// 첫 문자 " " 공백 3->4로?
                    return address.indexOf(str)+4;
                }else{
                    System.out.println(address.substring(address.indexOf(str)+3,address.length()));	// 없이 4로
                    return address.indexOf(str)+3;
                }
            }
        }
        System.out.println(arr2);

        for(String str : arr) {

            if (citycheck == 0) {
                if (str.equals("시") || str.equals("도")) {
                    System.out.println("check String: " + arr[citycheck]);
                    citycheck = i;
                }
            }else{
                if (str.equals("시")||str.equals("군")||str.equals("구")){
                    System.out.println(address.substring(0,i+1));
//                    break;
                    return i+1; // substring(a,b) b 전 인덱스
                }
            }
            i++;

        }
        return 0;
    }


}