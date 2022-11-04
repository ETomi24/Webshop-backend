package org.ewebshop.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ewebshop.category.Category;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    byte[] picture;
}
