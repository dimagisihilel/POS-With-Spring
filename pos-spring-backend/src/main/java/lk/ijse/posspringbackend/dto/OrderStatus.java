package lk.ijse.posspringbackend.dto;

import lk.ijse.posspringbackend.dto.impl.OrderDTO;

import java.io.Serializable;

public interface OrderStatus extends Serializable, SuperDTO {
    OrderDTO getOrderDTO();
}
