package lk.ijse.posspringbackend.dto.impl;

import lk.ijse.posspringbackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO implements SuperDTO {
    private Long id;
    private String itemId;
    private String orderId;
    private int qty;
    private double unitPrice;
    private double total;
}
