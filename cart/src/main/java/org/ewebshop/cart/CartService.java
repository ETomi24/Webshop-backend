package org.ewebshop.cart;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    public List<Cart> getAll(int orderId) {
        //TODO check if orderId valid
        return cartRepository.findAllByOrderId(orderId);
    }

    public boolean existsCheck(int productId, int orderId) {
        return cartRepository.existsById(new CartId(productId,orderId));
    }

    public void addCartItem(int productId, int orderId, int quantity) {
        //TODO product és order ellenörzés hogy valóban léteznek e
        Optional<Cart> cart = cartRepository.findById(new CartId(productId,orderId));
        if (cart.isPresent()) {
            Cart cartEntity = cart.get();
            cartEntity.setQuantity(cartEntity.getQuantity() + quantity);
            cartRepository.save(cartEntity);
        } else {
            Cart newCartEntity = new Cart(productId,orderId,quantity);
            cartRepository.save(newCartEntity);
        }
    }

    public void removeCartItem(int productId, int orderId, int quantity) {
        Optional<Cart> cart = cartRepository.findById(new CartId(productId,orderId));
        if (cart.isPresent()) {
            Cart cartEntity = cart.get();
            cartEntity.setQuantity(cartEntity.getQuantity() - quantity);
            cartRepository.save(cartEntity);
        } else {
            throw new EntityNotFoundException("CartItem is not existing");
        }
    }

}
