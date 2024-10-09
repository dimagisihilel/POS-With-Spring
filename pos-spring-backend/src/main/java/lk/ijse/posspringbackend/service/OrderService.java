package lk.ijse.posspringbackend.service;

import lk.ijse.posspringbackend.dto.OrderStatus;
import lk.ijse.posspringbackend.dto.impl.OrderDTO;

import java.util.List;

public interface OrderService {
    String generateNextOrderId();
    void saveOrder(OrderDTO orderDTO);
    OrderStatus getOrder(String orderId);
    void deleteOrder(String orderId);
    List<OrderDTO> getAllOrders();
    void updateOrder(String orderId, OrderDTO orderDTO);
}
