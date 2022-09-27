package org.ewebshop;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public void orderCreate(String userId) throws Exception {
        //check if user existing
        Boolean exist = restTemplate.getForObject(
                "http://USER/api/users/unique/{userId}",
                Boolean.class,
                userId
        );
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
