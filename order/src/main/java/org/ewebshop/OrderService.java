package org.ewebshop;

import lombok.AllArgsConstructor;
import org.ewebshop.clients.user.UserClient;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserClient userClient;

    public void orderCreate(String userId) throws EntityExistsException {
        //check if user existing
        Boolean exist = userClient.existsCheck(userId);
        if (Boolean.TRUE.equals(exist)) {
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

}
