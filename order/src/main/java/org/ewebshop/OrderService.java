package org.ewebshop;

import lombok.AllArgsConstructor;
import org.ewebshop.clients.user.UserClient;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserClient userClient;

    public Order getOrderById(int orderId) throws EntityNotFoundException{
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new EntityNotFoundException("Order is not existing");
        }

    }

    public List<Order> getFinishedOrdersByUserId(String userId) throws EntityExistsException{
        boolean exist = userClient.existsCheck(userId);
        if (exist) {
            return orderRepository.findAllByUserIdAndStatus(userId, Status.FINISHED);
        } else {
            throw new EntityExistsException("User is not existing");
        }
    }

    public void orderCreate(String userId) throws EntityExistsException {
        //check if user existing
        boolean exist = userClient.existsCheck(userId);
        if (exist) {
            orderRepository.save(Order.builder()
                    .userId(userId)
                    .creationDate(LocalDateTime.now())
                    .status(Status.IN_PROGRESS)
                    .build()
            );
        } else {
            throw new EntityExistsException("User is not existing");
        }
    }

    public boolean orderExists(int id) {
        return orderRepository.existsById(id);
    }

}
