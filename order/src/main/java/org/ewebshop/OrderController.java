package org.ewebshop;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
        }

    }

}
