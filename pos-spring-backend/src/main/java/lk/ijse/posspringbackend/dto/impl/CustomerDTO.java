package lk.ijse.posspringbackend.dto.impl;

import lk.ijse.posspringbackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements SuperDTO {
    private String customerId;
    private String name;
    private String address;
    private String contact;
}
