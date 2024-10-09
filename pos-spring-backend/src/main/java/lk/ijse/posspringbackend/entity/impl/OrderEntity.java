package lk.ijse.posspringbackend.entity.impl;


import jakarta.persistence.*;
import lk.ijse.posspringbackend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity implements SuperEntity {
    @Id
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    private Date orderDate;
    private double total;
    private double discount;
    private double newTotal;
    private double paidAmount;
    private double balance;

}
