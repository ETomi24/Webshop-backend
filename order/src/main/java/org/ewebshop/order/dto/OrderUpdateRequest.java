package org.ewebshop.order.dto;

import org.ewebshop.order.Status;

import java.time.LocalDateTime;

public record OrderUpdateRequest(
        Integer id,
        Long totalPrice,
        LocalDateTime creationDate,
        LocalDateTime completeDate,
        Status status,
        String userId
) {

}
