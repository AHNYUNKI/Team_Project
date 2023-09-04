package com.api.shop_project.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItem extends JpaRepository<com.api.shop_project.domain.order.OrderItem, Long> {
}
