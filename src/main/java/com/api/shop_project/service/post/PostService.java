package com.api.shop_project.service.post;

import com.api.shop_project.domain.post.Post;
import com.api.shop_project.dto.response.post.PostSave;
import com.api.shop_project.exception.PostNotFound;
import com.api.shop_project.repository.member.MemberRepository;
import com.api.shop_project.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public List<PostSave> postListDo() {
        List<PostSave> postSaves = new ArrayList<>();
        List<Post> posts = postRepository.findAll();

        for (Post post : posts) {
            postSaves.add(PostSave.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .writer(post.getWriter())
                    .member(post.getMember())
                    .replies((post.getReplies()))
                    .createTime(post.getCreateTime())
                    .upDateTime(post.getUpdateTime())
                    .build());
        }

        return postSaves;


    }

    @Transactional
    public Post postInsertDo(String title,
                             String content,
                             String writer
                             ) {



        Post post = postRepository.save(
                Post.builder()
                        .title(title)
                        .content(content)
                        .writer(writer)
//                        .member(postVo.getMember())
                        .build()
        );


        return post;
    }

    @Transactional
    public PostSave postDetail(Long id) {

        updateHit(id);


        Post post =
                postRepository.findById(id).orElseThrow(PostNotFound::new);

        return PostSave.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .createTime(post.getCreateTime())
                .upDateTime(post.getUpdateTime())
                .build();

    }

    @Transactional
    public int postUpdateOk(PostSave postSave) {


//        Post post = Post.toupdateOk(postVo);

        Post post = Post.builder()
                .id(postSave.getId())
                .writer(postSave.getWriter())
                .title(postSave.getTitle())
                .content(postSave.getContent())
                .build();


        Long postId = postRepository.save(post).getId();


        postRepository.findById(postId).orElseThrow(PostNotFound::new);

        return 1;


    }

    public PostSave postUpdate(Long id) {

        Post post =
                postRepository.findById(id).orElseThrow(PostNotFound::new);


        return PostSave.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .createTime(post.getCreateTime())
                .build();


    }

    public void postDelete(Long id) {

        postRepository.findById(id).orElseThrow(PostNotFound::new);

        postRepository.deleteById(id);

    }

    public List<PostSave> searchPostList(String subject, String search) {
        List<PostSave> postSaves = new ArrayList<>();
        List<Post> posts = new ArrayList<>();

//        switch (subject) {
//            case "id":
//                Long searchId = Long.parseLong(search);
//                posts = postRepository.findByIdContaining(searchId);
//                break;
//            case "title":
//                posts = postRepository.findByTitleContaining(search);
//                break;
//            case "writer":
//                posts = postRepository.findByWriterContaining(search);
//                break;
//            default:
//                posts = postRepository.findAll();
//        }
        if (subject.equals("content")) {
            posts = postRepository.findByContentContaining(search);
        } else if (subject.equals("title")) {
            posts = postRepository.findByTitleContaining(search);
        } else if (subject.equals("writer")) {
            posts = postRepository.findByWriterContaining(search);
        } else {
            posts = postRepository.findAll();
        }

        if (!posts.isEmpty()) {
            for (Post post : posts) {
                PostSave postSave = PostSave.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .writer(post.getWriter())
                        .createTime(post.getCreateTime())
                        .upDateTime(post.getUpdateTime())
                        .build();

                postSaves.add(postSave);
            }
        }
        return postSaves;


    }

    public Page<PostSave> postPagingList(Pageable pageable) {

        Page<Post> posts = postRepository.findAll(pageable);

        int nowPage = posts.getNumber(); // 현재 페이지 번호(요청 페이지 번호)
        Long totalCount = posts.getTotalElements(); // 전체 게시글 수
        int totalPage = posts.getTotalPages(); // 전체 페이지수
        int pageSize = posts.getSize(); // 한 페이지에 보이는 개수

        Page<PostSave> postVos = posts.map(PostSave::toBoardDto);

        return postVos;

    }

    public void updateHit(Long id){
        postRepository.updateHit(id);
    }


}






