package org.ewebshop.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, CartId> {
    List<Cart> findAllByOrderId(int orderId);
}