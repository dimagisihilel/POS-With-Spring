package lk.ijse.posspringbackend.customStatusCode;

import lk.ijse.posspringbackend.dto.CustomerStatus;
import lk.ijse.posspringbackend.dto.ItemStatus;
import lk.ijse.posspringbackend.dto.OrderDetailStatus;
import lk.ijse.posspringbackend.dto.OrderStatus;
import lk.ijse.posspringbackend.dto.impl.CustomerDTO;
import lk.ijse.posspringbackend.dto.impl.ItemDTO;
import lk.ijse.posspringbackend.dto.impl.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedErrorStatus implements CustomerStatus, ItemStatus, OrderStatus, OrderDetailStatus {
    private int statusCode;
    private String statusMessage;
    private CustomerDTO customerDTO;
    private ItemDTO itemDTO;
    private OrderDTO orderDTO;

    public SelectedErrorStatus(int i, String idNotValid) {

    }
    @Override
    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    @Override
    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    @Override
    public OrderDTO getOrderDTO() {

        return orderDTO;
    }

}
