package com.api.shop_project.controller.member;
import com.api.shop_project.dto.response.member.MemberDto;
import com.api.shop_project.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        return "member/join";
    }

//    @PostMapping("/join")
//    public String joinPost(MemberDto memberDto){
//
//        memberService.insertMember(memberDto);
//
//        return "redirect:/member/login";
//    }

//    @PostMapping("/join")
//    public String joinPost(@RequestParam String email,
//                           @RequestParam String password,
//                           @RequestParam String name){
//
//
//        memberService.insertMember(email, password, name);
//
//        return "redirect:/member/login";
//    }

    @PostMapping("/join")
    public String joinPost(@ModelAttribute MemberDto memberDto) {

        memberService.memberInsert(memberDto);

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
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


//    @PostMapping("/update")
//    public void update(
////            @PathVariable("memberId") Long memberId,
////                       @RequestParam String password,
////                       @RequestParam String name
//                       @ModelAttribute Update memberDto
////            ,
////                       @RequestParam String email,
////                       @RequestParam String address
//
//    ) {
//
////        memberService.update(memberId, password, name);
//
//        memberService.update(memberDto);
//
////        memberService.update(memberId, password, name, email, address);
////        memberService.updatMember(memberId, password, name, email, address);
//
////        memberService.update(memberId, password, name, email, address);
//
//    }

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
            return "member/memberUpdate";
        }
//        return "redirect:/member/memberList";
        return "redirect:/member/login";
    }



//    @PostMapping("/update")
//    public String update(MemberDto member, Model model) {
//
//        int rs = memberService.memberUpdateOk(memberDto);
//
//        if (rs == 1) {
//            System.out.println("수정 Success!!");
//        } else {
//            System.out.println("수정 Fail!!");
//        }
//
////    return "redirect:/member/memberList";
////    MemberDto member = memberService.updateMember(member.getId());
////
////    if (member != null) {
////        model.addAttribute("member", member);
////        // member_id에 해당사는 덧글 목록
////        List<ReplyDto> replyList = replyService.replyList(member.getId());
////        model.addAttribute("replyList", replyList);
////        return "member/memberDetail";
////    }
//        return "redirect:/member/memberList";
//    }

    @GetMapping
    public String ReSave(MemberDto memberDto , Model model) {

        model.addAttribute("memberDto", memberDto);

        return "/member/join";
    }

//    @PostMapping("/memberJoin")
//    public String ReSave2(@ModelAttribute MemberDto memberDto) {
//
//        memberService.memberInsert(memberDto);
//
//        return "/";
//
//    }


}
