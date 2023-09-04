package com.api.shop_project.service.review;

import com.api.shop_project.domain.Review;
import com.api.shop_project.domain.item.Filters;
import com.api.shop_project.domain.item.Top;
import com.api.shop_project.domain.member.Member;
import com.api.shop_project.domain.member.Role;
import com.api.shop_project.dto.response.review.ReviewList;
import com.api.shop_project.repository.Item.ItemRepository;
import com.api.shop_project.repository.member.MemberRepository;
import com.api.shop_project.repository.review.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewRepository reviewRepository;


    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
        itemRepository.deleteAll();
        reviewRepository.deleteAll();
    }

    @Test
    @DisplayName("리뷰 작성하기")
    public void test1() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .name("user")
                .password("1111")
                .phone("010-1111-1111")
                .role(Role.USER)
                .build();

        Member memberSave = memberRepository.save(member);

        Top top = Top.builder()
                .filters(Filters.MAN)
                .name("옷1")
                .price(10000)
                .stockQuantity(10)
                .top_Size("95")
                .build();

        Top itemSave = itemRepository.save(top);

        String review = "좋아요";

        // when
        reviewService.save(memberSave.getId(), itemSave.getId(), review);

        Review review1 = reviewRepository.findById(1L).get();
        // then
        Assertions.assertEquals("좋아요", review1.getContent());

    }
    @Test
    @DisplayName("리뷰 리스트")
    public void test2() {
        // given
        List<Member> member = IntStream.range(0,9)
                .mapToObj(i -> (Member.builder()
                        .email("user@gmail.com"+i)
                        .name("user"+i)
                        .password("1111")
                        .phone("010-1111-1111")
                        .role(Role.USER)
                        .build()
                )).collect(Collectors.toList());

        memberRepository.saveAll(member);

        Top top = Top.builder()
                .filters(Filters.MAN)
                .name("옷1")
                .price(10000)
                .stockQuantity(10)
                .top_Size("95")
                .build();
        // when
        List<ReviewList> list = reviewService.getList(top.getId());

        // then
        list.forEach(System.out::println);

    }

    @Test
    @DisplayName("리뷰 수정")
    public void test3() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .name("user")
                .password("1111")
                .phone("010-1111-1111")
                .role(Role.USER)
                .build();

        Member memberSave = memberRepository.save(member);

        Top top = Top.builder()
                .filters(Filters.MAN)
                .name("옷1")
                .price(10000)
                .stockQuantity(10)
                .top_Size("95")
                .build();

        Top itemSave = itemRepository.save(top);

        String review = "좋아요";

        Review save = reviewService.save(memberSave.getId(), itemSave.getId(), review);

        String content = "싫어요";
        // when
        reviewService.edit(save.getId(), content);

        // then
        Review review1 = reviewRepository.findById(save.getId()).get();

        assertEquals("싫어요", review1.getContent());
    }

    @Test
    @DisplayName("리뷰 삭제")
    public void test4() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .name("user")
                .password("1111")
                .phone("010-1111-1111")
                .role(Role.USER)
                .build();

        Member memberSave = memberRepository.save(member);

        Top top = Top.builder()
                .filters(Filters.MAN)
                .name("옷1")
                .price(10000)
                .stockQuantity(10)
                .top_Size("95")
                .build();

        Top itemSave = itemRepository.save(top);

        String review = "좋아요";

        Review save = reviewService.save(memberSave.getId(), itemSave.getId(), review);

        // when
        reviewService.delete(save.getId());

        // then

    }

}