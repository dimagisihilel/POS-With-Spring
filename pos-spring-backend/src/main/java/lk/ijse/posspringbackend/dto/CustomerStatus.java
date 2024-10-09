package lk.ijse.posspringbackend.dto;

import lk.ijse.posspringbackend.dto.impl.CustomerDTO;

import java.io.Serializable;

public interface CustomerStatus extends Serializable,SuperDTO {
    CustomerDTO getCustomerDTO();
}
