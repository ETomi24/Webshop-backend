package org.ewebshop.cart;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping("/all/{orderId}")
    public List<Cart> getAll(@PathVariable int orderId) {
        try {
            return cartService.getAll(orderId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PostMapping("/add/{orderId}/{productId}/{amount}")
    public void addItem(@PathVariable int orderId, @PathVariable int productId, @PathVariable int amount) {
        try{
            cartService.addCartItem(productId,orderId,amount);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PostMapping("/remove/{orderId}/{productId}/{amount}")
    public void removeItem(@PathVariable int orderId, @PathVariable int productId, @PathVariable int amount) {
        try{
            cartService.removeCartItem(productId, orderId, amount);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }



}
