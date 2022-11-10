package org.ewebshop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_id_sequence",
            sequenceName = "order_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_id_sequence"
    )
    private Integer id;
    private Long totalPrice;
    private LocalDateTime creationDate;
    private LocalDateTime completeDate;
    private Status status;
    private String userId;

}
