package com.api.shop_project.service.cart;

import com.api.shop_project.domain.cart.Cart;
import com.api.shop_project.domain.cart.CartItem;
import com.api.shop_project.domain.item.Item;
import com.api.shop_project.domain.member.Member;
import com.api.shop_project.domain.order.Order;
import com.api.shop_project.domain.order.OrderItem;
import com.api.shop_project.domain.order.OrderStatus;
import com.api.shop_project.dto.response.cart.CartFindOne;
import com.api.shop_project.repository.Item.ItemRepository;
import com.api.shop_project.repository.cart.CartItemRepository;
import com.api.shop_project.repository.cart.CartRepository;
import com.api.shop_project.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public void addCart(Long memberId, Long itemId, int count, int price) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );

        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalArgumentException("상품을 찾을 수 없습니다.")
        );

        Cart cart = cartRepository.save(Cart.builder()
                .member(member)
                .build());

        CartItem cartItem = cartItemRepository.save(CartItem.builder()
                .item(item)
                .count(count)
                .totalPrice(price)
                .cart(cart)
                .build());
    }

    public List<Cart> cartFindOne(Long memberId) {

        CartFindOne cartFindOne = new CartFindOne();

        cartFindOne.setMemberId(memberId);

         return cartRepository.cartFindOne(cartFindOne);


    }

    public void deleteCart(Long cartId) {

        Cart cartGet = cartRepository.findById(cartId).orElseThrow(() -> {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다.");
        });

        CartItem cartItem = cartItemRepository.findByCart(cartGet);

        cartItemRepository.delete(cartItem);

    }

}
