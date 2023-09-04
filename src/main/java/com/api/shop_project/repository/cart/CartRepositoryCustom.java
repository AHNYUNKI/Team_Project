package com.api.shop_project.repository.cart;

import com.api.shop_project.dto.response.cart.CartFindOne;
import com.querydsl.core.Tuple;

import java.util.List;

public interface CartRepositoryCustom{

    List<Tuple> cartFindOne(CartFindOne cartFindOne);
}
