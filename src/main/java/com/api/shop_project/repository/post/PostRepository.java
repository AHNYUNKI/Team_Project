package com.api.shop_project.repository.post;

import com.api.shop_project.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByIdContaining(Long searchId);

    List<Post> findByTitleContaining(String search);

    List<Post> findByWriterContaining(String search);

    List<Post> findByContentContaining(String search);

    @Transactional
    @Modifying
    @Query(value = "update Post p set p.hit = p.hit + 1 where p.id=:id")
    void updateHit(@Param("id") Long id);



}
