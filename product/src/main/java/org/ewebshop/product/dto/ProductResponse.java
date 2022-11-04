package org.ewebshop.product.dto;

import org.ewebshop.category.dto.CategoryResponse;
import org.ewebshop.product.Product;

import java.math.BigDecimal;

/**
 * A DTO for the {@link Product} entity
 */
public record ProductResponse(
        Integer id,
        String description,
        BigDecimal price,
        Integer quantity,
        String name,
        CategoryResponse category,
        byte[] picture
) {
}