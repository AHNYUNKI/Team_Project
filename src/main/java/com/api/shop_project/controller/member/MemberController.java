package com.api.shop_project.controller.member;

import com.api.shop_project.config.MyUserDetails;
import com.api.shop_project.domain.member.Member;
import com.api.shop_project.dto.response.member.MemberDto;
import com.api.shop_project.dto.response.member.MemberSave;
import com.api.shop_project.repository.member.MemberRepository;
import com.api.shop_project.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

        MemberDto member = memberService.memberDetail(id);

        model.addAttribute("member",member);
        model.addAttribute("myUserDetails",myUserDetails);

        return "member/detail";
    }

    //삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        int rs = memberService.memberDelete(id);
        if (rs == 1) {
            System.out.println("삭제 성공!!");
            return "redirect:/member/logout";

        } else {
            System.out.println("삭제 실패!!");
        }

//        return "redirect:/member/memberList";
        return "redirect:/index";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute MemberSave memberSave) {

        int rs = memberService.memberUpdate(memberSave);

        if (rs == 1) {
            System.out.println("수정 성공!!");
        } else {
            System.out.println("수정 실패!!");
        }
        return "redirect:/index";
    }

    //수정 페이지
    @GetMapping("/update/{id}")
    public String updateok(@PathVariable("id") Long id, Model model) {

        MemberDto memberdto = memberService.memberUpdateOk(id);

        MemberSave member = MemberSave.builder()
                .id(memberdto.getId())
                .name(memberdto.getName())
                .email(memberdto.getEmail())
                .password(memberdto.getPassword())
                .phone(memberdto.getPhone())
                .role(memberdto.getRole())
                .city(memberdto.getAddress().getCity())
                .street(memberdto.getAddress().getStreet())
                .zipcode(memberdto.getAddress().getZipcode())
                .build();

        if (member != null) {
            model.addAttribute("member", member);
            return "member/update";
        }
//        return "redirect:/member/memberList";
        return "redirect:/member/login";
    }

    @GetMapping("/oauth2add")
    public String oauth2addok(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model){
        Long memberid = myUserDetails.getMember().getId();

        MemberDto memberdto = memberService.memberUpdateOk(memberid);

        MemberSave member = MemberSave.builder()
                .id(memberdto.getId())
                .name(memberdto.getName())
                .email(memberdto.getEmail())
                .password(memberdto.getPassword())
                .phone(memberdto.getPhone())
                .role(memberdto.getRole())
                .city(memberdto.getAddress().getCity())
                .street(memberdto.getAddress().getStreet())
                .zipcode(memberdto.getAddress().getZipcode())
                .build();

        if (member != null) {
            model.addAttribute("member", member);
            return "member/oauth2add";
        }

        return "redirect:/index";
    }

    @PostMapping("/oauth2add")
    public String oauth2add(@AuthenticationPrincipal MyUserDetails myUserDetails, MemberSave memberSave){
        int rs = memberService.memberUpdate(memberSave);

        if (rs == 1) {
            System.out.println("수정 성공!!");


        } else {
            System.out.println("수정 실패!!");
        }
//        return "redirect:/member/userdata";

        return "redirect:/member/login";
    }

//    @GetMapping("/memberList")
//    public String memberList(Model model){
//        List<MemberDto> memberList = memberService.findAllList();
//
//        if (!memberList.isEmpty()) {
//            model.addAttribute("memberList", memberList);
//            return "member/memberList";
//        }
//
//        return "redirect:/index";
//    }
    @GetMapping("/memberList")
    public String memberList(@PageableDefault(page = 0, size = 5, sort = "id",
    direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        Page<MemberDto> memberList = memberService.pagingMemberList(pageable);

        int newPage = memberList.getNumber();
        int size = memberList.getSize();
        long totalElements = memberList.getTotalElements();
        int totalPages = memberList.getTotalPages();

        int blockNum = 5; //

        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPages ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPages);
        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

        if (!memberList.isEmpty()) {
            model.addAttribute("memberList", memberList);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            return "member/memberList";
        }
        return "member/memberList";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "search", required = false) String search,
            Model model){
        List<MemberDto> memberList = memberService.searchMemberList(subject, search);

        if (!memberList.isEmpty()){
            model.addAttribute("memberList", memberList);
            return "member/memberList";
        }

        return "member/memberList";

    }


    @GetMapping("/userdata")
    public String userData4(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model){
        Long memberid = myUserDetails.getMember().getId();

        Member member = memberRepository.findById(memberid).get();

        model.addAttribute("member",member);

        return "member/test";
    }

    @GetMapping("/emailChecked")
    public @ResponseBody int emailChecked(
            @RequestParam("email") String email) throws IOException {

        int rs= memberService.emailChecked(email);

        return rs;
    }

}
