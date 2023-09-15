package com.api.shop_project.repository.post;

import com.api.shop_project.domain.member.Member;
import com.api.shop_project.domain.post.Post;
import com.api.shop_project.dto.post.PostVo;
import com.api.shop_project.repository.member.MemberRepository;
import com.api.shop_project.service.post.PostService;
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
class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostService postService;

    @BeforeEach
    void test0() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 작성")
    public void test1() {

        Member member = Member.builder()
                .email("user1@naver.com")
                .password("1111")
                .name("a")
                .build();

        memberRepository.save(member);

        Member member1 = memberRepository.findById(1L).get();

        PostVo postVo = PostVo.builder()
                .title("h1")
                .content("내용")
                .writer(member1.getName())
                .member(member1)
                .build();

        postService.postInsertDo(postVo);


        Post post = postRepository.findById(1L).get();
        assertEquals("h1", post.getTitle());
        assertEquals("내용", post.getContent());
        assertEquals("a", post.getWriter());

    }

    @Test
    @DisplayName("게시글 LIST")
    public void test2(){


        List<Post> post = IntStream.range(0, 9)
                .mapToObj(i -> (Post.builder()
                        .title("제목1"+i)
                        .content("내용"+i)
                        .writer("작성자"+i)
                        .build()
                )).collect(Collectors.toList());

        postRepository.saveAll(post);

        post.forEach(System.out::println);






    }

}