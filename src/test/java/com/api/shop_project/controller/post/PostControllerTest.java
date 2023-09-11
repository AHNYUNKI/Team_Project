package com.api.shop_project.controller.post;

import com.api.shop_project.domain.member.Member;
import com.api.shop_project.dto.response.post.PostSave;
import com.api.shop_project.repository.member.MemberRepository;
import com.api.shop_project.repository.post.PostRepository;
import com.api.shop_project.repository.post.ReplyRepository;
import com.api.shop_project.service.post.PostService;
import com.api.shop_project.service.post.ReplyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.thymeleaf.check-template-location=false")
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
        postRepository.deleteAll();
        replyRepository.deleteAll();
    }

    @Test
    @DisplayName("/post 요청시 작성")
    public void test1() throws Exception {
        // given
        Member member = Member.builder()
                .email("user1@naver.com")
                .password("1111")
                .name("a")
                .build();

        Member member1 = memberRepository.save(member);

        PostSave postSave = PostSave.builder()
                .title("h1")
                .content("내용이무니다.")
                .writer(member1.getName())
                .member(member1)
                .build();

//        ObjectMapper objectMapper1 = objectMapper.writer

//        Post post = postService.postInsertDo(postVo);
//        String json = new Gson().toJson(postVo);

        String jsonPostVo = objectMapper.writeValueAsString(postSave);

        mockMvc.perform(post("/postInsert")
                        .param("title", String.valueOf(postSave.getTitle()))
                        .param("content", String.valueOf(postSave.getContent()))
                        .param("writer", String.valueOf(postSave.getWriter())))
                .andExpect(status().isOk())
                .andDo(print());

    }



}
