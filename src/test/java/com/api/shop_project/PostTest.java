package com.api.shop_project;

import com.api.shop_project.domain.post.Post;
import com.api.shop_project.repository.post.PostRepository;
import com.api.shop_project.dto.post.PostVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PostTest {

    @Autowired
    private PostRepository postRepository;

    // 공지글 List
    @Test
    void postListTest(){
        List<PostVo> postVos = new ArrayList<>();


        List<Post> posts
                = postRepository.findAll();

        for (Post post: posts){
            PostVo postVo = PostVo.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .writer(post.getWriter())
                    .member(post.getMember())
                    .build();
            postVos.add(postVo);
        }

        for (PostVo postVo : postVos){
            System.out.println("id : " + postVo.getId());
            System.out.println("title : " + postVo.getTitle());
            System.out.println("content : " + postVo.getContent());
            System.out.println("writer : " + postVo.getWriter());
            System.out.println("member : " + postVo.getMember());
            System.out.println();
        }



    }

    // 자세히 보기
    @Test
    void PostDetailTest(){

        Long id = 2L;

        Post post =
                postRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        PostVo postVo = PostVo.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .createTime(post.getCreateTime())
                .build();

        System.out.println(postVo.getId());
        System.out.println(postVo.getTitle());
        System.out.println(postVo.getContent());
        System.out.println(postVo.getWriter());
        System.out.println(postVo.getCreateTime());


    }

    @Test
    void PostUpdateTest(){

//        postVo.setId(2L);
//        postVo.setTitle("999");
//        postVo.setContent("999");
//        postVo.setWriter("999");

        Post post = Post.builder()
                .id(2L)
                .title("999")
                .content("999")
                .writer("999")
                .build();

        postRepository.save(post);

    }

    @Test
    void PostDeleteTest(){


        postRepository.deleteById(2L);

    }


}








