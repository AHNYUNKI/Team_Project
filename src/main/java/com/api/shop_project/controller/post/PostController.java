package com.api.shop_project.controller.post;



import com.api.shop_project.domain.post.Post;
import com.api.shop_project.dto.request.reply.ReplySave;
import com.api.shop_project.dto.response.post.PostSave;
import com.api.shop_project.service.post.PostService;
import com.api.shop_project.service.post.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final ReplyService replyService;
    
    // 공지사항 목록
    @GetMapping( {"" ,"/postList"})
    public String post(@PageableDefault(page = 0, size = 10, sort = "id",
                        direction = Sort.Direction.DESC)Pageable pageable ,Model model) {

//        List<PostVo> postList = postService.postListDo();
//
//        model.addAttribute("postList", postList);


        Page<PostSave> postVos = postService.postPagingList(pageable);
        Long totalCount = postVos.getTotalElements();
        int pagesize = postVos.getSize();
        int nowPage = postVos.getNumber();
        int totalPage = postVos.getTotalPages();
        int blockNum = 3;

        int startPage=
                (int) ((Math.floor(nowPage/blockNum)*blockNum)+1 <= totalPage ? (Math.floor(nowPage/blockNum)*blockNum)+1 : totalPage);

        int endPage =
                (startPage + blockNum - 1 < totalPage ? startPage + blockNum - 1 : totalPage);


        for(int i = startPage; i <= endPage; i++){
            System.out.println(i+" , ");
        }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("postVo", postVos);

//        return "post/postList";
        return "index";

    }


    // 글작성 페이지
    @GetMapping("/postInsert")
    public String postInsert(){


//      return "post/postInsert";
      return "index";
    }

    // 글작성
    @PostMapping("/postInsert")
    public String postInsertPost(@RequestParam String title,
                                 @RequestParam String content,
                                 @RequestParam String writer,
                                 Model model) {

//        if(bindingResult.hasErrors()){
//
//            return "post/postInsert";
//        }

        Post post = postService.postInsertDo(title,content,writer);

//        model.addAttribute("postVo", postVo);

//        return "redirect:/post/postList";
        return "index";
    }
    
    // 게시글 자세히 보기
    @GetMapping("/postDetail/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model){

        PostSave postSave = postService.postDetail(id);
        model.addAttribute("post", postSave);
        List<ReplySave> replyList = replyService.replyList(postSave.getId());
        model.addAttribute("replyList", replyList);


//        return "post/postDetail";
        return "index";

    }

    // update 버튼 클릭시
    @PostMapping("/postUpdateOk")
    public String postUpdateOk(PostSave postSave, Model model){

        int rs = postService.postUpdateOk(postSave);

        if(rs == 1) {
            System.out.println("수정성공");
//            model.addAttribute("postVo", postVo1);

            return "redirect:/post/postList";
        } else{
            System.out.println("수정실패");
        }

        return "redirect:/post/postList";

    }
    
    // update 페이지로 이동
    @GetMapping("/postUpdate/{id}")
    public String postUpdate(@PathVariable("id") Long id, Model model){

        PostSave postSave1 = postService.postUpdate(id);

        if (postSave1 != null) {
            model.addAttribute("post", postSave1);

            return "post/postUpdate";

        }

        return "redirect:/post/postList";

    }

    @GetMapping("/postDelete/{id}")
    public String postDelete(@PathVariable("id") Long id){

        postService.postDelete(id);

        return "redirect:/post/postList";

    }

    // 게시글 검색
    @GetMapping("/postSearch")
    public String postSearch(
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "search", required = false) String search,
            Model model){

        System.out.println(subject + " subject");
        System.out.println(search + " search");

        List<PostSave> postSaves
                = postService.searchPostList(subject, search);

        if(!postSaves.isEmpty()){
            System.out.println("완료");
            model.addAttribute("postList", postSaves);
            return "post/postList";
        }

        System.out.println("조회할 목록이 없다.");
        return "redirect:/post/postList";

    }


    //    @GetMapping("/pagingList")
//    public String pagingList(@PageableDefault(page = 0, size = 10, sort = "id",
//            direction = Sort.Direction.DESC)Pageable pageable, Model model){
//
//        Page<PostVo> postVos = postService.postPagingList(pageable);
//
//        Long totalCount = postVos.getTotalElements();
//
//        int pageSize = postVos.getSize();
//
//        int nowPage = postVos.getNumber();
//        int totalPage = postVos.getTotalPages();
//        int blockNum = 3;
//
//        int startPage=
//                (int) ((Math.floor(nowPage/blockNum)*blockNum)+1 <= totalPage ? (Math.floor(nowPage/blockNum)*blockNum) : totalPage);
//
//        int endPage =
//                (startPage + blockNum - 1 < totalPage ? startPage + blockNum - 1 : totalPage);
//
//
//        for(int i = startPage; i <= endPage; i++){
//            System.out.println(i+" , ");
//        }
//
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("postVo", postVos);
//
//
//        return "post/postList";
//    }



}








