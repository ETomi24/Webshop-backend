package org.ewebshop;

import lombok.AllArgsConstructor;
import org.ewebshop.clients.user.UserClient;
import org.ewebshop.dto.OrderCreateRequest;
import org.ewebshop.dto.OrderUpdateRequest;
import org.ewebshop.exception.IdNotMatchingException;
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

    public List<Order> getFinishedOrdersByUserId(String userId) throws EntityNotFoundException{
        boolean exist = userClient.existsCheck(userId);
        if (exist) {
            return orderRepository.findAllByUserIdAndStatus(userId, Status.FINISHED);
        } else {
            throw new EntityNotFoundException("User is not existing");
        }
    }

    public Order getInProgressOrderByUserId(String userId) throws EntityNotFoundException {
        boolean exist = userClient.existsCheck(userId);
        if (exist) {
            Optional<Order> order = orderRepository.findByUserIdAndStatus(userId, Status.IN_PROGRESS);
            if(order.isPresent()){
                return order.get();
            } else {
                throw new EntityNotFoundException("Order is not existing");
            }
        } else {
            throw new EntityNotFoundException("User is not existing");
        }
    }

    public void completeOrder(int id) throws EntityExistsException, EntityNotFoundException {
        Order order = getOrderById(id);
        order.setStatus(Status.FINISHED);
        orderRepository.save(order);
    }

    public void orderUpdate(int id, OrderUpdateRequest orderUpdateRequest) throws IdNotMatchingException, EntityNotFoundException {
        if (id != orderUpdateRequest.id()) {
            throw new IdNotMatchingException();
        }
        Order order = this.getOrderById(id);
        order.setStatus(orderUpdateRequest.status());
        order.setDeliveryDate(orderUpdateRequest.deliveryDate());
        order.setCreationDate(orderUpdateRequest.creationDate());
        order.setTotalPrice(orderUpdateRequest.totalPrice());
    }

    public void orderCreate(OrderCreateRequest orderCreateRequest) throws EntityNotFoundException {
        //check if user existing
        boolean userExists = userClient.existsCheck(orderCreateRequest.userId());
        Optional<Order> order = orderRepository.findByUserIdAndStatus(orderCreateRequest.userId(), Status.IN_PROGRESS);
        if (order.isEmpty()){
            if (userExists) {
                orderRepository.save(Order.builder()
                        .userId(orderCreateRequest.userId())
                        .creationDate(LocalDateTime.now())
                        .status(Status.IN_PROGRESS)
                        .build()
                );
            } else {
                throw new EntityNotFoundException("User is not existing");
            }
        } else {
            throw new EntityExistsException("This user already have an In_Progress order");
        }

    }

    public boolean orderExists(int id) {
        return orderRepository.existsById(id);
    }

}
