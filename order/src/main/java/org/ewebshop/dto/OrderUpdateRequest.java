package org.ewebshop.dto;

import org.ewebshop.Status;

import java.time.LocalDateTime;

public record OrderUpdateRequest(
        Integer id,
        Long totalPrice,
        LocalDateTime creationDate,
        LocalDateTime deliveryDate,
        Status status,
        String userId
) {

}
