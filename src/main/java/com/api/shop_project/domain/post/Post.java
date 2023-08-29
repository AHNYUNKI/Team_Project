package com.api.shop_project.domain.post;

<<<<<<< HEAD
import com.api.shop_project.domain.BaseTime;
import com.api.shop_project.domain.member.Member;
import com.api.shop_project.dto.post.PostVo;
import lombok.*;
=======
import com.api.shop_project.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
>>>>>>> 5c8e7fb138c77e53f420c48b8749179e66abb5b1

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
<<<<<<< HEAD
@Setter
@Entity
@Table(name = "posts")
public class Post extends BaseTime {
=======
@Entity
public class Post {
>>>>>>> 5c8e7fb138c77e53f420c48b8749179e66abb5b1

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

<<<<<<< HEAD
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
=======
    private String title;

    private String content;

>>>>>>> 5c8e7fb138c77e53f420c48b8749179e66abb5b1
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();
<<<<<<< HEAD



}





=======
}
>>>>>>> 5c8e7fb138c77e53f420c48b8749179e66abb5b1
