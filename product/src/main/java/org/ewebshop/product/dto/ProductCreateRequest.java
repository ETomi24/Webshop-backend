package org.ewebshop.product.dto;

import java.math.BigDecimal;

public record ProductCreateRequest(
        String description,
        BigDecimal price,
        Integer quantity,
        String name,
        String category,
        String picture
) {
}
