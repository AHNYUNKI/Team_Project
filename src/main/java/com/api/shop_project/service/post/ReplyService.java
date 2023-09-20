package com.api.shop_project.service.post;

import com.api.shop_project.domain.member.Member;
import com.api.shop_project.domain.post.Post;
import com.api.shop_project.domain.post.Reply;
import com.api.shop_project.dto.request.reply.ReplySave;
import com.api.shop_project.exception.PostNotFound;
import com.api.shop_project.exception.ReplyNotFound;
import com.api.shop_project.repository.member.MemberRepository;
import com.api.shop_project.repository.post.PostRepository;
import com.api.shop_project.repository.post.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;




    @Transactional
    public Reply replyInsert(Long postId, String content, String email) {



        ReplySave replySave = new ReplySave();

//        Member member1 = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        Member member = (Member) memberRepository.findByName(email).orElseThrow(()-> new IllegalArgumentException("이메일 찾을 수 없습니다."));

        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);

        Reply reply = replyRepository.save(Reply.builder()
                .content(content)
                .writer(member.getName())
                .member(member)
                .post(post)
                .build());


        // 게시물 ID 가져오기
//        Long postId = replyVo.getPost().getId();
        // 게시물 검색
//        Optional<Post> optionalPost = postRepository.findById(postId);

        // 게시글 id
//        Optional<Post> optionalPost =
//                Optional.ofNullable(postRepository.findById(replyVo.getId()).orElseThrow(() -> {
//                    return new IllegalArgumentException("아이디가 존재하지 않음");
//                }));

//        if (optionalPost.isPresent()) {
//            // 게시글 id(Post_id)
//            Post post = new Post();
////            post.setId(replyVo.getId());
//
//            ReplyVo replyVo1 = new ReplyVo();
//            replyVo1.setPost(post);
//            replyVo1.setTitle(replyVo.getTitle());
//            replyVo1.setContent(replyVo.getContent());
//            replyVo1.setWriter(replyVo.getWriter());
//
//            return replyRepository.save(Reply.builder()
//                    .title(replyVo1.getTitle())
//                    .content(replyVo1.getContent())
//                    .writer(replyVo1.getWriter())
//                    .post(replyVo1.getPost())
//                    .build());
//
////            Post post = optionalPost.get();
//
//            // Reply 엔티티 생성 및 저장
////            Reply reply = Reply.builder()
////                    .post(post)
////                    .title(replyVo.getTitle())
////                    .content(replyVo.getContent())
////                    .writer(replyVo.getWriter())
////                    .build();
////
////            return replyRepository.save(reply);
//
//        } else{
//            throw new IllegalArgumentException("게시글이 존재하지 않음");
//        }

        return reply;
    }

    public List<ReplySave> replyList(Long id) {

        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.get();
        List<ReplySave> replyVos = new ArrayList<>();
        List<Reply> replyList = replyRepository.findAllByPost(post);
        for (Reply reply : replyList) {
            ReplySave replyVo = ReplySave.builder()
                    .id(reply.getId())
                    .content(reply.getContent())
                    .writer(reply.getWriter())
                    .member(reply.getMember())
                    .post(reply.getPost())
                    .createTime(reply.getCreateTime())
                    .updateTime(reply.getUpdateTime())
                    .build();

            replyVos.add(replyVo);
        }

        return replyVos;

    }


    @Transactional
    public Reply replyUpdateOk(String email, Long replyId, Long memberId, Long postId, String content) {

        Member member = (Member) memberRepository.findByName(email).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));


//        Reply reply = Reply.builder()
//                .title(title)
//                .content(content)
//                .member(member)
//                .writer(member.getName())
//                .post(post)
//                .build();
//
//        Long replyId = replyRepository.save(reply).getId();

//        Reply reply1 = replyRepository.findByMemberIdAndPostId(memberId,postId);
//
//        if(reply1 == null){
//            throw new ReplyNotFound();
//
//        }

        Reply reply = Reply.builder()
                .id(replyId)
                .content(content)
                .member(member)
                .writer(member.getName())
                .post(post)
                .build();


//        return replyRepository.save(reply);
        return replyRepository.save(reply);

    }


    public Reply replyDelete(Long id) {

//        Optional<Reply> optionalReply =
//                Optional.ofNullable(replyRepository.findById(id).orElseThrow(ReplyNotFound::new));
        Reply reply = replyRepository.findById(id).orElseThrow(ReplyNotFound::new);

        replyRepository.delete(reply);

        return reply;
    }


    public ReplySave replyUpdate(Long id) {

        Optional<Reply> optionalReplySave =
                Optional.ofNullable(replyRepository.findById(id).orElseThrow(()->{
                    return new IllegalArgumentException("업데이트할 아이디 없음");
                }));

        if (optionalReplySave.isPresent()){

            ReplySave replySave =
                    ReplySave.builder()
                            .id(optionalReplySave.get().getId())
                            .content(optionalReplySave.get().getContent())
                            .writer(optionalReplySave.get().getWriter())
                            .createTime(optionalReplySave.get().getCreateTime())
                            .updateTime(optionalReplySave.get().getUpdateTime())
                            .post(optionalReplySave.get().getPost())
                            .build();

            return replySave;
        }
        return null;

    }

}






