package com.api.shop_project.repository.cart;

import com.api.shop_project.domain.cart.QCart;
import com.api.shop_project.domain.cart.QCartItem;
import com.api.shop_project.domain.item.QItem;
import com.api.shop_project.domain.member.QMember;
import com.api.shop_project.dto.response.cart.CartFindOne;
import com.api.shop_project.dto.response.cart.CartResponse;
import com.api.shop_project.exception.ValueException;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.api.shop_project.domain.member.QMember.member;

@RequiredArgsConstructor
public class CartRepositoryCustomImpl implements CartRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public List<CartResponse> cartFindOne(CartFindOne cartFindOne) {

        QCartItem cartItem = QCartItem.cartItem;
        QCart cart = QCart.cart;
        QMember member = QMember.member;
        QItem item = QItem.item;

        return query.select(Projections.bean(CartResponse.class,
                cart.member.id,
                item.name,
                cartItem.count,
                cartItem.totalPrice))
                .from(cartItem)
                .join(cart).on(cartItem.cart.id.eq(cart.id))
                .join(item).on(cartItem.item.id.eq(item.id))
                .where(cart.member.id.eq(cartFindOne.getMemberId()))  // Assuming memberId is of type Long
                .fetch();

//        return query.select(cartItem)
//                .from(cartItem)
//                .join(cartItem.cart, cart)
//                .join(cartItem.item, item)
//                .where(cartIdLike((cartFindOne.getMemberId())))
//                .limit(1000)
//                .fetch();


//        return query.select(cart)
//                .from(cart)
//                .join(cart.member, member)
//                .where(cartIdLike(cartFindOne.getMemberId()))
//                .limit(1000)
//                .fetch();

    }

    private BooleanExpression cartIdLike(Long memberId) {
        if (memberId == null) {
            throw new ValueException();
        }

        return member.id.ne(memberId);

    }

}
