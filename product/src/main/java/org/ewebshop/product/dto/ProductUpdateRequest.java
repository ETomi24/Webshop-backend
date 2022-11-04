package org.ewebshop.product.dto;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        Integer id,
        String description,
        BigDecimal price,
        Integer quantity,
        String name,
        String category,
        String picture
) {

}
