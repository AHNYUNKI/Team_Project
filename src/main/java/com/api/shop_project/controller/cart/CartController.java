package com.api.shop_project.controller.cart;

import com.api.shop_project.config.UserPrincipal;
import com.api.shop_project.domain.cart.Cart;
import com.api.shop_project.domain.cart.CartItem;
import com.api.shop_project.service.cart.CartService;
import lombok.NoArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping("/cart/add")
    public String addCart(@AuthenticationPrincipal UserPrincipal principal,
                        @RequestParam Long itemId,
                        @RequestParam int count,
                        @RequestParam int total) {
        cartService.addCart(principal.getMemberId(), itemId, count, total);

        return "/carts";

    }

    @GetMapping("/carts")
    public String cartList(@AuthenticationPrincipal UserPrincipal principal, Model model) {

        List<CartItem> carts = cartService.cartFindOne(principal.getMemberId());

        model.addAttribute("carts", carts);

        return "/cart/cart";
    }

    @PostMapping("/carts/{itemId}")
    public String cartCancel(@PathVariable("itemId") Long itemId) {

        cartService.cartCancel(itemId);

        return "/cart/cart";

    }
}
