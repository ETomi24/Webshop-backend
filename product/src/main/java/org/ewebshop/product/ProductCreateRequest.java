package org.ewebshop.product;

import java.math.BigDecimal;

public record ProductCreateRequest(
        String description,
        BigDecimal price,
        Integer quantity,
        String name,
        String categoryId,
        String pictureUrl
) {
}
