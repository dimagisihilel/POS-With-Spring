package lk.ijse.posspringbackend.customStatusCode;

import lk.ijse.posspringbackend.dto.CustomerStatus;
import lk.ijse.posspringbackend.dto.ItemStatus;
import lk.ijse.posspringbackend.dto.OrderDetailStatus;
import lk.ijse.posspringbackend.dto.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedErrorStatus implements CustomerStatus, ItemStatus, OrderStatus, OrderDetailStatus {
    private int statusCode;
    private String statusMessage;
}
