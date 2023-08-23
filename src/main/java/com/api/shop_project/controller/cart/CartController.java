package com.api.shop_project.controller.cart;

import com.api.shop_project.domain.cart.Cart;
import com.api.shop_project.service.cart.CartService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@NoArgsConstructor
public class CartController {

    private CartService cartService;


    @PostMapping("/carts")
    public void addCart(@RequestParam Long memberId,
                        @RequestParam Long itemId,
                        @RequestParam int count,
                        @RequestParam int total) {
        cartService.addCart(memberId, itemId, count, total);
    }

    @GetMapping("/carts/{memberId}")
    public void cartList(@PathVariable("memberId") Long memberId, Model model) {

        List<Cart> carts = cartService.cartFindOne(memberId);

        model.addAttribute("carts", carts);

//        return "/";
    }
}
