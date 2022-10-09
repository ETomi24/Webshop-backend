package org.ewebshop;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @RequestMapping("/create/{username}")
    public ResponseEntity<?> createOrder(@PathVariable String username) {
        try{
            orderService.orderCreate(username);
            return new ResponseEntity<>("Order created", HttpStatus.OK);
        } catch (EntityExistsException exception) {
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
        }

    }

}
