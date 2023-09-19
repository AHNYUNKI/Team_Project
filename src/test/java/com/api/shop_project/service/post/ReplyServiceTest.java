//package com.api.shop_project.service.post;
//
//import com.api.shop_project.domain.member.Member;
//import com.api.shop_project.domain.member.Role;
//import com.api.shop_project.domain.post.Post;
//import com.api.shop_project.domain.post.Reply;
//import com.api.shop_project.dto.request.reply.ReplySave;
//import com.api.shop_project.repository.member.MemberRepository;
//import com.api.shop_project.repository.post.PostRepository;
//import com.api.shop_project.repository.post.ReplyRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@SpringBootTest
//public class ReplyServiceTest {
//
//
//    @Autowired
//    private PostService postService;
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private ReplyService replyService;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private ReplyRepository replyRepository;
//
//
//
//    @BeforeEach
//    void test0() {
//        postRepository.deleteAll();
//        replyRepository.deleteAll();
//    }
//
//    @Test
//    @DisplayName("댓글 작성")
//    void test1() {
//        // given
//
//        Member memberSave = Member.builder()
//                .email("user@gmail.com3")
//                .name("user")
//                .password("1111")
//                .phone("010-1111-1111")
//                .role(Role.USER)
//                .build();
//
//        Member member = memberRepository.save(memberSave);
//
//        Post postSave = Post.builder()
//                .title("게시글 제목")
//                .content("게시글 내용")
//                .writer(member.getName())
//                .build();
//
//        Post post = postRepository.save(postSave);
//
//
////        Post post1 = postRepository.save(post);
//
//
//        // when
//        ReplySave replySave = ReplySave.builder()
//                .content("내용1")
//                .writer(member.getName())
//                .post(post)
//                .member(member)
//                .build();
//
//        replyService.replyInsert(member.getId(), post.getId(), replySave.getContent());
//
////        Reply saveReply = replyRepository.save(reply);
//
//
//        // then
//        Reply reply = replyRepository.findAll().get(0);
//        Assertions.assertNotNull(reply.getId());
//        Assertions.assertEquals(1L, reply.getId());
//        Assertions.assertEquals("내용1", reply.getContent());
//        // 그냥 글 작성자랑 댓글 작성자랑 통일 시켜봄..
//
//
//    }
//
//    @Test
//    @DisplayName("댓글 리스트")
//    void test2() {
//
//        // given
//        Member member = Member.builder()
//                .email("user3@naver.com")
//                .password("3333")
//                .name("c")
//                .build();
//
//        Member member1 = memberRepository.save(member);
//
//        Post postSave = Post.builder()
//                .title("title2")
//                .content("content2")
//                .writer(member1.getName())
//                .member(member1)
//                .build();
//
////        Post post1 = postRepository.save(post);
//        Post post1 = postRepository.save(postSave);
//
//        // when
//        List<Reply> replies = IntStream.range(0, 9)
//                .mapToObj(i -> (Reply.builder()
//                        .post(post1)
//                        .member(member1)
//                        .content("내용임" + i)
//                        .writer(member1.getName())
//                        .build()
//                )).collect(Collectors.toList());
//
//
//        System.out.println("잘됐나?");
//        replies.forEach(System.out::println);
//
//        List<Reply> list = replyRepository.saveAll(replies);
//
//        list.get(0).getContent();
//
////        ReplyRepository
//
//    }
//
//    @Test
//    @DisplayName("댓글수정")
//    void test3(){
//
//        // given
//        Member memberSave = Member.builder()
//                .email("user@gmail.com3")
//                .name("user")
//                .password("1111")
//                .phone("010-1111-1111")
//                .role(Role.USER)
//                .build();
//
//        Member member = memberRepository.save(memberSave);
//
//        Post postSave = Post.builder()
//                .title("게시글 제목")
//                .content("게시글 내용")
//                .writer(member.getName())
//                .build();
//
//        Post post = postRepository.save(postSave);
//
//
//        ReplySave replySave = ReplySave.builder()
//                .content("내용1")
//                .writer(member.getName())
//                .post(post)
//                .member(member)
//                .build();
//
//        Reply reply = replyService.replyInsert(member.getId(), post.getId(), replySave.getContent());
//
//
//        // when
//        Long memberId = 1L;
//        Long postId = 1L;
////        String originalTitle = "제목1";
////        String originalContent = "내용1";
////
////        Reply saveReply = replyService.replyUpdateOk(memberId, postId, originalTitle, originalContent);
//
//        String updatedTitle = "제목2";
//        String updatedContent = "내용2";
//
//
//
//        // then
//        Reply updatedReply = replyService.replyUpdateOk(reply.getId(),memberId,postId,updatedContent);
//        Assertions.assertNotNull(updatedReply);
//        Assertions.assertEquals(updatedContent,updatedReply.getContent());
////        Reply reply = replyRepository.findAll().get(0);
////        Assertions.assertNotNull(reply.getId());
////        Assertions.assertEquals("제목2", reply.getTitle());
////        Assertions.assertEquals("내용2", reply.getContent());
//        // 그냥 글 작성자랑 댓글 작성자랑 통일 시켜봄..
//    }
//
//
//    @Test
//    @DisplayName("댓글 삭제")
//    void test4(){
//        // given
//        Member memberSave = Member.builder()
//                .email("user@gmail.com3")
//                .name("user")
//                .password("1111")
//                .phone("010-1111-1111")
//                .role(Role.USER)
//                .build();
//
//        Member member = memberRepository.save(memberSave);
//
//        Post postSave = Post.builder()
//                .title("게시글 제목")
//                .content("게시글 내용")
//                .writer(member.getName())
//                .build();
//
//        Post post = postRepository.save(postSave);
//
//
//        ReplySave replySave = ReplySave.builder()
//                .content("내용1")
//                .writer(member.getName())
//                .post(post)
//                .member(member)
//                .build();
//
//        Reply reply = replyService.replyInsert(member.getId(), post.getId(), replySave.getContent());
//
//
//
//        replyService.replyDelete(reply.getId());
//    }
//
//
//}
//
//
//
//
//
//
//
