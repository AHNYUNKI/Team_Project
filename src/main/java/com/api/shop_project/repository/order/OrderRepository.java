package com.api.shop_project.repository.order;

import com.api.shop_project.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface OrderRepository extends JpaRepository<Order, Long> {
=======
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom{
>>>>>>> 5c8e7fb138c77e53f420c48b8749179e66abb5b1
}
