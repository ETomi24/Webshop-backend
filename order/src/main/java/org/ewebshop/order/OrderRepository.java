package org.ewebshop.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserIdAndStatus(String userId, Status status);

    Optional<Order> findByUserIdAndStatus(String userId, Status status);
}