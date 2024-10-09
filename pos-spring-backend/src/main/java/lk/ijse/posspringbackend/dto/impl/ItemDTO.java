package lk.ijse.posspringbackend.dto.impl;

import lk.ijse.posspringbackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO implements SuperDTO {
    private String itemId;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
}
