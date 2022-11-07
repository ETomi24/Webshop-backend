package org.ewebshop.cart;

import lombok.AllArgsConstructor;
import org.ewebshop.clients.order.OrderClient;
import org.ewebshop.clients.product.ProductClient;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final OrderClient orderClient;
    private final ProductClient productClient;

    public List<Cart> getAll(int orderId) {
        if (orderClient.existsCheck(orderId)){
            return cartRepository.findAllByOrderId(orderId);
        } else {
            throw new EntityNotFoundException("Order is not existing");
        }
    }

    public boolean existsCheck(int productId, int orderId) {
        return cartRepository.existsById(new CartId(productId,orderId));
    }

    public void addCartItem(int productId, int orderId, int quantity) {
        if (orderClient.existsCheck(orderId)){
            if (productClient.existsCheck(productId)) {
                Optional<Cart> cart = cartRepository.findById(new CartId(productId,orderId));
                if (cart.isPresent()) {
                    Cart cartEntity = cart.get();
                    cartEntity.setQuantity(cartEntity.getQuantity() + quantity);
                    cartRepository.save(cartEntity);
                    //TODO csökkenteni a product mennyiséget a raktárban
                    productClient.decreaseQuantity(productId, quantity);
                } else {
                    Cart newCartEntity = new Cart(productId,orderId,quantity);
                    cartRepository.save(newCartEntity);
                    //TODO csökkenteni a product mennyiséget a raktárban
                    productClient.decreaseQuantity(productId, quantity);
                }
            } else {
                throw new EntityNotFoundException("Product is not existing");
            }
        } else {
            throw new EntityNotFoundException("Order is not existing");
        }
    }

    public void removeCartItem(int productId, int orderId, int quantity) {
        //TODO rename delete cart item if quantity zero
        Optional<Cart> cart = cartRepository.findById(new CartId(productId,orderId));
        if (cart.isPresent()) {
            Cart cartEntity = cart.get();
            cartEntity.setQuantity(cartEntity.getQuantity() - quantity);
            cartRepository.save(cartEntity);
            productClient.increaseQuantity(productId, quantity);
        } else {
            throw new EntityNotFoundException("CartItem is not existing");
        }
    }

}
