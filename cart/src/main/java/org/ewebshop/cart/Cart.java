package org.ewebshop.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(CartId.class)
@Table (name = "cart")
public class Cart {

    @Id
    private int productId;
    @Id
    private int orderId;

    private int quantity;

}
