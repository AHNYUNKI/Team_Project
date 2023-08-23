package com.api.shop_project.repository.cart;

import com.api.shop_project.domain.cart.Cart;
import com.api.shop_project.domain.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCart(Cart cartGet);
}
