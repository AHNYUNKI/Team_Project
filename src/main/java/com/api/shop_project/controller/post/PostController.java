package com.api.shop_project.controller.post;


import com.api.shop_project.vo.post.PostVo;
import com.api.shop_project.repository.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping( {"" ,"/postList"})
    public String post(Model model) {
        List<PostVo> postList = postService.postListDo();

        model.addAttribute("postList", postList);
        return "post/postList";

    }

    @GetMapping("/postInsert")
    public String postInsert(){


      return "post/postInsert";
    }

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

    @GetMapping("/postDetail/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model){

        PostVo postVo = postService.postDetail(id);

        model.addAttribute("post",postVo);

        return "post/postDetail";

    }

    @PostMapping("/postUpdate")
    public String postUpdate(PostVo postVo, Model model){

        PostVo postVo1 = postService.postUpdate(postVo);

        model.addAttribute("postVo",postVo1);


        return "post/postDetail";

    }





}
