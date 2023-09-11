package com.api.shop_project.service.review;

import com.api.shop_project.domain.Review;
import com.api.shop_project.domain.item.Item;
import com.api.shop_project.domain.member.Member;
import com.api.shop_project.dto.response.review.ReviewList;
import com.api.shop_project.repository.Item.ItemRepository;
import com.api.shop_project.repository.member.MemberRepository;
import com.api.shop_project.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Review save(Long memberId, Long itemId, String content) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        Review review = Review.builder()
                .content(content)
                .member(member)
                .item(item)
                .build();

        return reviewRepository.save(review);

    }

    public List<ReviewList> getList(Long itemId) {

        List<Review> reviews = reviewRepository.findByItemId(itemId);

        return reviews.stream()
                .map(review -> ReviewList.builder()
                .content(review.getContent())
                .member(review.getMember())
                .build()).collect(Collectors.toList());

    }

    @Transactional
    public void edit(Long reviewId, String content) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("리뷰가 없습니다."));

        review.edit(content);

    }

    @Transactional
    public void delete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("리뷰가 없습니다."));

        reviewRepository.delete(review);
    }

}
