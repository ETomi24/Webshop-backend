package org.ewebshop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByStatus(Status status);

    List<Order> findAllByUserIdAndStatus(String userId, Status status);
}