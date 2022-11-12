package org.ewebshop.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ewebshop.order.dto.OrderCreateRequest;
import org.ewebshop.order.dto.OrderUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        try{
            orderService.orderCreate(orderCreateRequest);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id) {
        try {
            return orderService.getOrderById(id);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PatchMapping ("/complete/{id}")
    public void completeOrder(@PathVariable Integer id) {
        try {
            orderService.completeOrder(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }

    }

    @PutMapping("/update/{id}")
    public void updateOrder(@PathVariable Integer id, @RequestBody OrderUpdateRequest orderUpdateRequest){
        try {
            orderService.orderUpdate(id, orderUpdateRequest);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/finished/{username}")
    public List<Order> getFinishedOrders(@PathVariable String username) {
        try {
            return orderService.getFinishedOrdersByUserId(username);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/in_progress/{username}")
    public Order getInProgressOrder(@PathVariable String username) {
        try {
            return orderService.getInProgressOrderByUserId(username);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/exists/{id}")
    public boolean existsCheck(@PathVariable Integer id) {
        try {
            return orderService.orderExists(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        try {
            orderService.orderDelete(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
