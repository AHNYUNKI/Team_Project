package com.api.shop_project.dto.response.review;

import com.api.shop_project.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewList {

    private final String content;

    private final Member member;

    @Builder
    public ReviewList(String content, Member member) {
        this.content = content;
        this.member = member;
    }
}
