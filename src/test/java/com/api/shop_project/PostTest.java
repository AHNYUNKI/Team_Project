package com.api.shop_project;

import com.api.shop_project.domain.post.Post;
import com.api.shop_project.repository.post.PostRepository;
import com.api.shop_project.vo.post.PostVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PostTest {

    @Autowired
    private PostRepository postRepository;


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


}








