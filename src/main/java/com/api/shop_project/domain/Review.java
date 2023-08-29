package com.api.shop_project.domain;


import com.api.shop_project.domain.item.Item;
import com.api.shop_project.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import lombok.experimental.SuperBuilder;
=======
>>>>>>> 5c8e7fb138c77e53f420c48b8749179e66abb5b1

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Review extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

<<<<<<< HEAD
=======
    public void edit(String content) {
        this.content = content;
    }
>>>>>>> 5c8e7fb138c77e53f420c48b8749179e66abb5b1
}
