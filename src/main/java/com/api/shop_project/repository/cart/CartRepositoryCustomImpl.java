package com.api.shop_project.repository.cart;

import com.api.shop_project.domain.cart.Cart;
import com.api.shop_project.domain.cart.QCart;
import com.api.shop_project.domain.member.QMember;
import com.api.shop_project.dto.response.cart.CartFindOne;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.api.shop_project.domain.member.QMember.member;

@RequiredArgsConstructor
public class CartRepositoryCustomImpl implements CartRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public List<Cart> cartFindOne(CartFindOne cartFindOne) {

        QCart cart = QCart.cart;
        QMember member = QMember.member;

        return query.select(cart)
                .from(cart)
                .join(cart.member, member)
                .where(cartIdLike(cartFindOne.getMemberId()))
                .limit(1000)
                .fetch();

    }

    private BooleanExpression cartIdLike(Long memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다.");
        }

        return member.id.ne(memberId);

    }

}
