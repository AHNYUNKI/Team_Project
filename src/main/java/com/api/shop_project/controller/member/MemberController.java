package com.api.shop_project.controller.member;

import com.api.shop_project.config.MyUserDetails;
import com.api.shop_project.config.UserPrincipal;
import com.api.shop_project.domain.member.Member;
import com.api.shop_project.dto.response.member.MemberDto;
import com.api.shop_project.dto.response.member.MemberSave;
import com.api.shop_project.repository.member.MemberRepository;
import com.api.shop_project.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    @GetMapping("/join")
    public String join2(Model model) {

        MemberSave memberSave = new MemberSave();

        model.addAttribute("memberSave", memberSave);

        return "member/join";
    }

    @PostMapping("/join")
    public String joinPost2(@ModelAttribute MemberSave memberSave){

        memberService.insertMember2(memberSave);

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal MyUserDetails myUserDetails){

        MemberDto memberDto = memberService.memberDetail(id);

        model.addAttribute("memberDto",memberDto);
        model.addAttribute("myUserDetails",myUserDetails);

        return "member/detail";
    }

    //삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        int rs = memberService.memberDelete(id);
        if (rs == 1) {
            System.out.println("삭제 Success!!");
        } else {
            System.out.println("삭제 Fail!!");
        }

//        return "redirect:/member/memberList";
        return "redirect:/member/join";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute MemberDto memberDto
//            , Model model
    ) {

        int rs = memberService.memberUpdate(memberDto);

        if (rs == 1) {
            System.out.println("수정 Success!!");
        } else {
            System.out.println("수정 Fail!!");
        }
        return "redirect:/member/join";
//        return "redirect:/member/memberList";
    }


    //수정 페이지
    @GetMapping("/update/{id}")
    public String updateok(@PathVariable("id") Long id, Model model) {

        MemberDto member = memberService.memberUpdateOk(id);


        if (member != null) {
            model.addAttribute("member", member);
            return "member/update";
        }
//        return "redirect:/member/memberList";
        return "redirect:/member/login";
    }

    @GetMapping("/memberList")
    public String memberList(Model model){
        List<MemberDto> memberList = memberService.findAllList();

        if (!memberList.isEmpty()) {
            model.addAttribute("memberList", memberList);
            return "member/memberList";
        }

        return "redirect:/index";
    }


    @GetMapping("/userdata")
    public String userData4(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model){
        Long memberid = myUserDetails.getMember().getId();

        Member member = memberRepository.findById(memberid).get();

        model.addAttribute("member",member);

        return "member/test";
    }

}
