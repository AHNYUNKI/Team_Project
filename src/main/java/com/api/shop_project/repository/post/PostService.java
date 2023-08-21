package com.api.shop_project.repository.post;

import com.api.shop_project.domain.post.Post;
import com.api.shop_project.vo.post.PostVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public List<PostVo> postListDo() {
        List<PostVo> postVos = new ArrayList<>();
        List<Post> posts = postRepository.findAll();

        for (Post post : posts) {
            postVos.add(PostVo.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .writer(post.getWriter())
                    .member(post.getMember())
                    .replies((post.getReplies()))
                    .createTime(post.getCreateTime())
                    .build());
        }

        return postVos;


    }

    @Transactional
    public void postInsertDo(PostVo postVo) throws IllegalAccessException {

//        Post post = Post.builder()
//                .title(postVo.getTitle())
//                .content(postVo.getContent())
//                .writer(postVo.getWriter())
//                .build();


        postRepository.save(
                Post.builder().title(postVo.getTitle()).content(postVo.getContent()).writer(postVo.getWriter())
                        .build()
        );


    }

    @Transactional
    public PostVo postDetail(Long id) {

        Post post =
                postRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return PostVo.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .createTime(post.getCreateTime())
                .build();

    }

    @Transactional
    public PostVo postUpdate(PostVo postVo) {

        Post post = Post.builder()
                .id(postVo.getId())
                .title(postVo.getTitle())
                .content(postVo.getContent())
                .writer(postVo.getWriter())
                .build();

        postRepository.save(post);

        return PostVo.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .createTime(post.getCreateTime())
                .build();


    }
}
