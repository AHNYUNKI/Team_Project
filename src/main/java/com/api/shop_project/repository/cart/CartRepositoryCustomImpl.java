package com.api.shop_project.repository.cart;

import com.api.shop_project.domain.cart.QCart;
import com.api.shop_project.domain.cart.QCartItem;
import com.api.shop_project.domain.item.QItem;
import com.api.shop_project.domain.member.QMember;
import com.api.shop_project.dto.response.cart.CartFindOne;
import com.api.shop_project.dto.response.cart.CartResponse;
import com.api.shop_project.exception.ValueException;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.api.shop_project.domain.member.QMember.member;

@RequiredArgsConstructor
public class CartRepositoryCustomImpl implements CartRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public List<CartResponse> cartFindOne(CartFindOne cartFindOne) {
        if (cartFindOne == null) {
            // Handle the case where cartFindOne is null
            return Collections.emptyList(); // Or throw an exception or handle it based on your requirement
        }

        QCartItem cartItem = QCartItem.cartItem;
        QCart cart = QCart.cart;
        QMember member = QMember.member;
        QItem item = QItem.item;

        List<Tuple> queryResults = query
                .select(
                        item.id,
                        item.name,
                        cartItem.count.sum(),
                        item.price,
                        cartItem.totalPrice.sum())
                .from(cartItem)
                .join(cart).on(cartItem.cart.id.eq(cart.id))
                .join(item).on(cartItem.item.id.eq(item.id))
                .where(cart.member.id.eq(cartFindOne.getMemberId()))
                .groupBy(item.id)
                .fetch();

        List<CartResponse> cartResponses = new ArrayList<>();

        for (Tuple tuple : queryResults) {
            CartResponse cartResponse = new CartResponse();
            cartResponse.setItemId(tuple.get(item.id));
            cartResponse.setName(tuple.get(item.name));
            cartResponse.setCount(tuple.get(cartItem.count.sum()));
            cartResponse.setPrice(tuple.get(item.price));
            cartResponse.setTotalPrice(tuple.get(cartItem.totalPrice.sum()));
            cartResponses.add(cartResponse);
        }

        return cartResponses;

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
