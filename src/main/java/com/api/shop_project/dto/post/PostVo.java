package com.api.shop_project.dto.post;

import com.api.shop_project.domain.member.Member;
import com.api.shop_project.domain.post.Post;
import com.api.shop_project.domain.post.Reply;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostVo {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private Member member;

    private LocalDateTime createTime;

    private LocalDateTime upDateTime;

    private List<Reply> replies;

    public static PostVo toBoardDto(Post post) {

        PostVo postVo = new PostVo();
        postVo.setId(post.getId());
        postVo.setTitle(post.getTitle());
        postVo.setContent(post.getContent());
        postVo.setWriter(post.getWriter());
        postVo.setCreateTime(post.getCreateTime());
        postVo.setUpDateTime(post.getUpdateTime());

        return postVo;
    }



}
