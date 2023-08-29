package com.api.shop_project.repository.cart;

import com.api.shop_project.domain.cart.CartItem;
import com.api.shop_project.dto.response.cart.CartFindOne;

import java.util.List;

public interface CartRepositoryCustom{

    List<CartItem> cartFindOne(CartFindOne cartFindOne);
}
