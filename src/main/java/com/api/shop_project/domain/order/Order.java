package com.api.shop_project.domain.order;

import com.api.shop_project.domain.BaseTime;
import com.api.shop_project.domain.member.Member;
import lombok.*;
<<<<<<< HEAD
import lombok.experimental.SuperBuilder;
=======
>>>>>>> 5c8e7fb138c77e53f420c48b8749179e66abb5b1

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
@Builder
@AllArgsConstructor
=======
>>>>>>> 5c8e7fb138c77e53f420c48b8749179e66abb5b1
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "orders")
public class Order extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

<<<<<<< HEAD
    private int count;

=======
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(Long id, Member member, OrderStatus orderStatus ) {
        this.id = id;
        this.member = member;
        this.orderStatus = orderStatus;
    }
>>>>>>> 5c8e7fb138c77e53f420c48b8749179e66abb5b1
}
