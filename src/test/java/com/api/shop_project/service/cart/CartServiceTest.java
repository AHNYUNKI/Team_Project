package com.api.shop_project.service.cart;

import com.api.shop_project.domain.cart.Cart;
import com.api.shop_project.domain.item.Filters;
import com.api.shop_project.domain.item.Top;
import com.api.shop_project.domain.member.Member;
import com.api.shop_project.domain.member.Role;
import com.api.shop_project.repository.Item.ItemRepository;
import com.api.shop_project.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
class CartServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartService cartService;



    @Test
    @DisplayName("물건 담기")
    public void test1() {
        // given
        Top top = Top.builder()
                .filters(Filters.MAN)
                .name("옷1")
                .price(10000)
                .stockQuantity(10)
                .top_Size("95")
                .build();

        Long itemId = itemRepository.save(top).getId();


        Member member = Member.builder()
                .email("user@gmail.com")
                .name("user")
                .password("1111")
                .phone("010-1111-1111")
                .role(Role.USER)
                .build();

        Long memberId = memberRepository.save(member).getId();


        // when

        int total = top.getPrice() * 10;

        cartService.addCart(memberId, itemId, top.getStockQuantity(), total);

        // then
    }

    @Test
    @DisplayName("장바구니 리스트")
    public void test2() {
        // given
//        Member member = Member.builder()
//                .email("user@gmail.com")
//                .name("user")
//                .password("1111")
//                .phone("010-1111-1111")
//                .role(Role.USER)
//                .build();
//
//        Long memberId = memberRepository.save(member).getId();
//
//        List<Item> itemList = IntStream.range(0, 10)
//                .mapToObj(i -> Top.builder()
//                        .filters(Filters.MAN)
//                        .name("옷" + i)
//                        .top_Size("95")
//                        .stockQuantity(5)
//                        .price(10000)
//                        .build())
//                .collect(Collectors.toList());
//
//        List<Item> itemList1 = itemRepository.saveAll(itemList);
//
//        cartService.addCart(memberId, itemList1.get(1).getId(), itemList1.get(1).getStockQuantity(), 100);
//        cartService.addCart(memberId, itemList1.get(2).getId(), itemList1.get(2).getStockQuantity(), 100);
//        cartService.addCart(memberId, itemList1.get(3).getId(), itemList1.get(3).getStockQuantity(), 1100);
//        cartService.addCart(memberId, itemList1.get(4).getId(), itemList1.get(4).getStockQuantity(), 100);

//        // when
//        List<CartList> all = cartService.findALL(memberId);
//
//        // then
//        for (CartList carts : all ) {
//            System.out.println("장바구니 =>" + carts.getCartItems());
//        }

        List<Cart> all = cartService.cartFindOne(1L);

        for (Cart cart : all) {
            System.out.println(cart.getCartItems());
        }

    }

}