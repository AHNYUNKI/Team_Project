package com.api.shop_project.controller.post;

import com.api.shop_project.domain.member.Member;
import com.api.shop_project.domain.member.Role;
import com.api.shop_project.domain.post.Post;
import com.api.shop_project.domain.post.Reply;
import com.api.shop_project.dto.request.reply.ReplySave;
import com.api.shop_project.repository.member.MemberRepository;
import com.api.shop_project.repository.post.PostRepository;
import com.api.shop_project.repository.post.ReplyRepository;
import com.api.shop_project.service.post.PostService;
import com.api.shop_project.service.post.ReplyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.thymeleaf.check-template-location=false")
public class ReplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ReplyRepository replyRepository;


    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
        postRepository.deleteAll();
        replyRepository.deleteAll();
    }


    @Test
    @DisplayName("/reply/write 시 댓글 작성")
    public void test1() throws Exception {
        Member memberSave = Member.builder()
                .email("user@gmail.com3")
                .name("user")
                .password("1111")
                .phone("010-1111-1111")
                .role(Role.USER)
                .build();

        Member member = memberRepository.save(memberSave);

        Post postSave = Post.builder()
                .title("게시글 제목")
                .content("게시글 내용")
                .writer(member.getName())
                .build();

        Post post = postRepository.save(postSave);


//        Post post1 = postRepository.save(post);


        // when
        ReplySave replySave = ReplySave.builder()
                .content("내용1")
                .writer(member.getName())
                .post(post)
                .member(member)
                .build();

//        replyService.replyInsert(replySave, member.getId(), post.getId());

//        Reply saveReply = replyRepository.save(reply);

        mockMvc.perform(post("/reply/write")
                        .param("memberId", String.valueOf(member.getId()))
                        .param("postId", String.valueOf(post.getId()))
                        .param("content", replySave.getContent()))
                .andExpect(status().isOk())
                .andDo(print());

        // then

    }


    @Test
    @DisplayName("/postList 일시 페이징 확인")
    public void test2() throws Exception {

        // given
        Member memberSave = Member.builder()
                .email("user@gmail.com3")
                .name("user")
                .password("1111")
                .phone("010-1111-1111")
                .role(Role.USER)
                .build();

        Member member = memberRepository.save(memberSave);

        Post postSave = Post.builder()
                .title("게시글 제목")
                .content("게시글 내용")
                .writer(member.getName())
                .build();

        Post post = postRepository.save(postSave);

        List<Reply> replies = IntStream.range(0, 20)
                .mapToObj(i -> (Reply.builder()
                        .post(post)
                        .member(member)
                        .content("내용임" + i)
                        .writer(member.getName())
                        .build()
                )).collect(Collectors.toList());


        // when
        List<Reply> list = replyRepository.saveAll(replies);


        mockMvc.perform(MockMvcRequestBuilders.get("/postList")
                        .param("page", "0") // 테스트할 페이지 번호
                        .param("size", "20") // 페이지당 아이템 개수
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index")) // 예상하는 뷰 이름
                .andExpect(MockMvcResultMatchers.model().attributeExists("postVo")) // 모델 속성 확인
                .andExpect(MockMvcResultMatchers.model().attributeExists("startPage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("endPage"))
                .andDo(print());


//        Post post1 = postRepository.save(post);


    }

    @Test
    @DisplayName("댓글 업데이트")
    public void test3() throws Exception {
        // given
        Member memberSave = Member.builder()
                .email("user@gmail.com3")
                .name("user")
                .password("1111")
                .phone("010-1111-1111")
                .role(Role.USER)
                .build();

        Member member = memberRepository.save(memberSave);

        Post postSave = Post.builder()
                .title("게시글 제목")
                .content("게시글 내용")
                .writer(member.getName())
                .build();

        Post post = postRepository.save(postSave);


        ReplySave replySave = ReplySave.builder()
                .content("내용1")
                .writer(member.getName())
                .post(post)
                .member(member)
                .build();

        Reply reply = replyService.replyInsert(post.getId(), replySave.getContent(), member.getEmail());


        // when
        Long memberId = 1L;
        Long postId = 1L;
//        String originalTitle = "제목1";
//        String originalContent = "내용1";
//
//        Reply saveReply = replyService.replyUpdateOk(memberId, postId, originalTitle, originalContent);

        String updatedTitle = "제목2";
        String updatedContent = "내용2";


        mockMvc.perform(post("/reply/update")
                        .param("replyId", String.valueOf(reply.getId()))
                        .param("memberId", String.valueOf(memberId))
                        .param("postId", String.valueOf(postId))
                        .param("title", updatedTitle)
                        .param("content", updatedContent))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("댓글 삭제")
    public void test4() throws Exception {
        // given
        Member memberSave = Member.builder()
                .email("user@gmail.com3")
                .name("user")
                .password("1111")
                .phone("010-1111-1111")
                .role(Role.USER)
                .build();

        Member member = memberRepository.save(memberSave);

        Post postSave = Post.builder()
                .title("게시글 제목")
                .content("게시글 내용")
                .writer(member.getName())
                .build();

        Post post = postRepository.save(postSave);


        // when
        ReplySave replySave = ReplySave.builder()
                .content("내용1")
                .writer(member.getName())
                .post(post)
                .member(member)
                .build();

        Reply reply = replyService.replyInsert(post.getId(), replySave.getContent(), member.getEmail());

        //
        mockMvc.perform(post("/reply/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(print());


    }


}








