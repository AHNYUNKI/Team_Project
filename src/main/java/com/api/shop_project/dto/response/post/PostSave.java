package com.api.shop_project.dto.response.post;

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
public class PostSave {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private int hit;

    private Member member;

    private LocalDateTime createTime;

    private LocalDateTime upDateTime;

    private List<Reply> replies;

    public static PostSave toBoardDto(Post post) {

        PostSave postSave = new PostSave();
        postSave.setId(post.getId());
        postSave.setTitle(post.getTitle());
        postSave.setContent(post.getContent());
        postSave.setWriter(post.getWriter());
        postSave.setCreateTime(post.getCreateTime());
        postSave.setUpDateTime(post.getUpdateTime());

        return postSave;
    }



}
