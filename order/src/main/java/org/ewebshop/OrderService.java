package org.ewebshop;

import lombok.AllArgsConstructor;
import org.ewebshop.clients.user.UserClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserClient userClient;

    public void orderCreate(String userId) throws Exception {
        //check if user existing
        Boolean exist = userClient.uniqueCheck(userId);
        if (Boolean.TRUE.equals(exist)) {
            orderRepository.save(Order.builder()
                    .userId(userId)
                    .creationDate(LocalDateTime.now())
                    .status(Status.IN_PROGRESS)
                    .build()
            );
        } else {
            throw new Exception("User is not existing");
        }

    }

}
