package com.api.shop_project.vo.post;

import com.api.shop_project.domain.member.Member;
import com.api.shop_project.domain.post.Reply;
import lombok.*;
import org.springframework.stereotype.Service;

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

}
