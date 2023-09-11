package com.api.shop_project.controller.post;

import com.api.shop_project.dto.request.reply.ReplySave;
import com.api.shop_project.service.post.PostService;
import com.api.shop_project.service.post.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final PostService postService;
    private final ReplyService replyService;



    @PostMapping("/write")
    public String replyWrite(@Valid @RequestParam Long memberId,
                             @RequestParam Long postId,
                             @RequestParam String title,
                             @RequestParam String content){

        replyService.replyInsert(memberId, postId, title, content);

        return "index";

    }

    @PostMapping("/update")
    public String replyUpdate(@Valid @RequestParam Long replyId,
                              @RequestParam Long memberId,
                              @RequestParam Long postId,
                              @RequestParam String title,
                              @RequestParam String content){

        replyService.replyUpdateOk(replyId, memberId, postId, title, content);

        return "index";

    }

    @PostMapping("/delete/{id}")
    public String replyDelete(@PathVariable("id") Long id, Model model){

        replyService.replyDelete(id);

        return "index";
    }


}


