package com.api.shop_project.repository.post;

import com.api.shop_project.domain.post.Post;
<<<<<<< HEAD
import com.api.shop_project.dto.post.PostVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByIdContaining(Long searchId);

    List<Post> findByTitleContaining(String search);

    List<Post> findByWriterContaining(String search);

    List<Post> findByContentContaining(String search);
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
>>>>>>> 5c8e7fb138c77e53f420c48b8749179e66abb5b1
}
