package com.api.shop_project.repository.post;

import com.api.shop_project.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByIdContaining(Long searchId);

    List<Post> findByTitleContaining(String search);

    List<Post> findByWriterContaining(String search);

    List<Post> findByContentContaining(String search);

}
