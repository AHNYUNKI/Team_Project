package com.api.shop_project.controller.post;


//import com.api.shop_project.domain.post.Post;
//import com.api.shop_project.vo.post.PostVo;
//import com.api.shop_project.repository.post.PostService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;

import com.api.shop_project.dto.post.PostVo;
import com.api.shop_project.repository.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    
    // 공지사항 목록
    @GetMapping( {"" ,"/postList"})
    public String post(@PageableDefault(page = 0, size = 10, sort = "id",
                        direction = Sort.Direction.DESC)Pageable pageable ,Model model) {

//        List<PostVo> postList = postService.postListDo();
//
//        model.addAttribute("postList", postList);


        Page<PostVo> postVos = postService.postPagingList(pageable);
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

        return "post/postList";

    }


    // 글작성 페이지
    @GetMapping("/postInsert")
    public String postInsert(){


      return "post/postInsert";
    }

    // 글작성
    @PostMapping("/postInsert")
    public String postInsertPost(@Valid PostVo postVo,
                                 BindingResult bindingResult,
                                 Model model) throws IllegalAccessException {

        if(bindingResult.hasErrors()){

            return "post/postInsert";
        }

        postService.postInsertDo(postVo);

        model.addAttribute("postVo", postVo);

        return "redirect:/post/postList";
    }
    
    // 게시글 자세히 보기
    @GetMapping("/postDetail/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model){

        PostVo postVo = postService.postDetail(id);

        model.addAttribute("post",postVo);

        return "post/postDetail";

    }

    // update 버튼 클릭시
    @PostMapping("/postUpdateOk")
    public String postUpdateOk(PostVo postVo, Model model){

        int rs = postService.postUpdateOk(postVo);

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

        PostVo postVo1 = postService.postUpdate(id);

        if (postVo1 != null) {
            model.addAttribute("post",postVo1);

            return "post/postUpdate";

        }

        return "redirect:/post/postList";

    }

    @GetMapping("/postDelete/{id}")
    public String postDelete(@PathVariable("id") Long id){

        postService.postDelete(id);

        return "redirect:/post/postList";

    }

    @GetMapping("/postSearch")
    public String postSearch(
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "search", required = false) String search,
            Model model){

        System.out.println(subject + " subject");
        System.out.println(search + " search");

        List<PostVo> postVos
                = postService.searchPostList(subject, search);

        if(!postVos.isEmpty()){
            System.out.println("완료");
            model.addAttribute("postList",postVos);
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








