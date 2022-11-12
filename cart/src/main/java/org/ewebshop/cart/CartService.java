package org.ewebshop.cart;

import lombok.AllArgsConstructor;
import org.ewebshop.commons.clients.order.OrderClient;
import org.ewebshop.commons.clients.product.ProductClient;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if (orderClient.existsCheck(orderId, "Bearer " + (String) SecurityContextHolder.getContext().getAuthentication().getCredentials())){
            return cartRepository.findAllByOrderId(orderId);
        } else {
            throw new EntityNotFoundException("Order is not existing");
        }
    }

    public boolean existsCheck(int productId, int orderId) {
        return cartRepository.existsById(new CartId(productId,orderId));
    }

    public void addCartItem(int productId, int orderId, int quantity) {
        if (orderClient.existsCheck(orderId, "Bearer " +  (String) SecurityContextHolder.getContext().getAuthentication().getCredentials())){
            if (productClient.existsCheck(productId)) {
                Optional<Cart> cart = cartRepository.findById(new CartId(productId,orderId));
                if (cart.isPresent()) {
                    Cart cartEntity = cart.get();
                    cartEntity.setQuantity(cartEntity.getQuantity() + quantity);
                    //csökkentem a product mennyiséget a raktárban
                    productClient.decreaseQuantity(productId, quantity,"Bearer " +  (String) SecurityContextHolder.getContext().getAuthentication().getCredentials());
                    cartRepository.save(cartEntity);
                } else {
                    Cart newCartEntity = new Cart(productId,orderId,quantity);
                    //csökkentem a product mennyiséget a raktárban
                    productClient.decreaseQuantity(productId, quantity,"Bearer " +  (String) SecurityContextHolder.getContext().getAuthentication().getCredentials());
                    cartRepository.save(newCartEntity);
                }
            } else {
                throw new EntityNotFoundException("Product is not existing");
            }
        } else {
            throw new EntityNotFoundException("Order is not existing");
        }
    }

    public void removeCartItem(int productId, int orderId, int quantity) {
        if (orderClient.existsCheck(orderId , "Bearer " +  (String) SecurityContextHolder.getContext().getAuthentication().getCredentials())) {
            Optional<Cart> cart = cartRepository.findById(new CartId(productId,orderId));
            if (cart.isPresent()) {
                Cart cartEntity = cart.get();
                cartEntity.setQuantity(cartEntity.getQuantity() - quantity);
                if (cartEntity.getQuantity() <= 0) {
                    cartRepository.delete(cartEntity);
                } else {
                    cartRepository.save(cartEntity);
                }
                productClient.increaseQuantity(productId, quantity,"Bearer " +  (String) SecurityContextHolder.getContext().getAuthentication().getCredentials());

            } else {
                throw new EntityNotFoundException("CartItem is not existing");
            }
        } else {
            throw new EntityNotFoundException("Order is not existing");
        }

    }

}
