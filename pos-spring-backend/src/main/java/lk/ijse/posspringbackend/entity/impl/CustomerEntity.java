package lk.ijse.posspringbackend.entity.impl;


import jakarta.persistence.*;
import lk.ijse.posspringbackend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity implements SuperEntity {
    @Id
    private String customerId;
    private String name;
    private String address;
    private String contact;

    // One customer can have many orders
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<OrderEntity> orders;

}
