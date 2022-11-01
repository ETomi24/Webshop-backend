package org.ewebshop.product;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        Integer id,
        String description,
        BigDecimal price,
        Integer quantity,
        String name,
        String categoryId,
        String picture
) {

}
