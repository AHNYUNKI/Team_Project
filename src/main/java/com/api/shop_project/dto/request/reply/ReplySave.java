package com.api.shop_project.dto.request.reply;

import com.api.shop_project.domain.BaseTime;
import com.api.shop_project.domain.member.Member;
import com.api.shop_project.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ReplySave extends BaseTime {

    private Long id;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    private String writer;

    private Post post;

    private Member member;

    @Builder
    public ReplySave(Long id, String title, String content, String writer, Post post, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.post = post;
        this.member = member;
    }
}
