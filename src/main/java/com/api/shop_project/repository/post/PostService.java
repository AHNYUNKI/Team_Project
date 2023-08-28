package com.api.shop_project.repository.post;

import com.api.shop_project.domain.post.Post;
import com.api.shop_project.dto.post.PostVo;
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
                    .upDateTime(post.getUpdateTime())
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
                Post.builder()
                        .title(postVo.getTitle())
                        .content(postVo.getContent())
                        .writer(postVo.getWriter())
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
                .upDateTime(post.getUpdateTime())
                .build();

    }

    @Transactional
    public int postUpdateOk(PostVo postVo) {


//        Post post = Post.toupdateOk(postVo);

        Post post = Post.builder()
                .id(postVo.getId())
                .writer(postVo.getWriter())
                .title(postVo.getTitle())
                .content(postVo.getContent())
                .build();


        Long postId = postRepository.save(post).getId();


        Optional<Post> optionalPost =
                Optional.ofNullable(postRepository.findById(postId).orElseThrow(() -> {
                    return new IllegalArgumentException("수정 아이디가 없습니다.");
                }));

        if (optionalPost.isPresent()) {

            return 1;
        }

        System.out.println("안됐다!");

        return 0;


    }

    public PostVo postUpdate(Long id) {

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

    public void postDelete(Long id) {

        postRepository.deleteById(id);

    }

    public List<PostVo> searchPostList(String subject, String search) {
        List<PostVo> postVos = new ArrayList<>();
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
        if(subject.equals("content")){
            posts = postRepository.findByContentContaining(search);
        } else if (subject.equals("title")) {
            posts = postRepository.findByTitleContaining(search);
        } else if (subject.equals("writer")) {
            posts = postRepository.findByWriterContaining(search);
        } else{
            posts = postRepository.findAll();
        }

        if(!posts.isEmpty()){
            for(Post post : posts){
                PostVo postVo = PostVo.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .writer(post.getWriter())
                        .createTime(post.getCreateTime())
                        .upDateTime(post.getUpdateTime())
                        .build();

                postVos.add(postVo);
            }
        }
        return postVos;


    }

    public Page<PostVo> postPagingList(Pageable pageable) {

        Page<Post> posts = postRepository.findAll(pageable);

        int nowPage = posts.getNumber(); // 현재 페이지 번호(요청 페이지 번호)
        Long totalCount = posts.getTotalElements(); // 전체 게시글 수
        int totalPage = posts.getTotalPages(); // 전체 페이지수
        int pageSize = posts.getSize(); // 한 페이지에 보이는 개수

        Page<PostVo> postVos = posts.map(PostVo :: toBoardDto);

        return postVos;

    }
}






