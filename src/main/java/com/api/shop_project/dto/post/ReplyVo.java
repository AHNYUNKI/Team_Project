package com.api.shop_project.dto.post;

import com.api.shop_project.domain.member.Member;
import com.api.shop_project.domain.post.Post;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReplyVo {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private Post post;

    private Member member;


}
