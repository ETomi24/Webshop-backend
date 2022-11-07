package org.ewebshop;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ewebshop.dto.OrderCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        try{
            orderService.orderCreate(orderCreateRequest);
            return new ResponseEntity<>("Order created", HttpStatus.OK);
        } catch (EntityExistsException exception) {
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
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
}
