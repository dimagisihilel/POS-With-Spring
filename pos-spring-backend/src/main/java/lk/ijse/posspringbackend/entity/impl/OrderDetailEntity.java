package lk.ijse.posspringbackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.posspringbackend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailEntity implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order; // Relationship with Order

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item; // Relationship with Item

    private int qty;
    private double unitPrice;
    private double total;
}
