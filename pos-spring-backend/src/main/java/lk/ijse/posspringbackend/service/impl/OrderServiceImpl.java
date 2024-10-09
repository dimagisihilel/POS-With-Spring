package lk.ijse.posspringbackend.service.impl;

import lk.ijse.posspringbackend.customStatusCode.SelectedErrorStatus;
import lk.ijse.posspringbackend.dao.OrderDao;
import lk.ijse.posspringbackend.dto.OrderStatus;
import lk.ijse.posspringbackend.dto.impl.OrderDTO;
import lk.ijse.posspringbackend.entity.impl.OrderEntity;
import lk.ijse.posspringbackend.exception.DataPersistException;
import lk.ijse.posspringbackend.exception.OrderNotFoundException;
import lk.ijse.posspringbackend.service.OrderService;
import lk.ijse.posspringbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private Mapping mapping;

    public String generateNextOrderId() {
        String lastOrderId = orderDao.getLastOrderId();
        if (lastOrderId == null || lastOrderId.isEmpty()) {
            return "ORD001";
        }

        int nextId = Integer.parseInt(lastOrderId.replace("ORD", "")) + 1;
        return String.format("ORD%03d", nextId);
    }
    @Override
    public void saveOrder(OrderDTO orderDTO) {
        OrderEntity savedOrder = orderDao.save(mapping.toOrderEntity(orderDTO));
        if (savedOrder == null) {
            throw new DataPersistException("Failed to save order");
        }
    }
    @Override
    public OrderStatus getOrder(String orderId) {
        if (orderDao.existsById(orderId)) {
            OrderEntity selectedOrder = orderDao.getReferenceById(orderId);
            OrderDTO orderDTO = mapping.toOrderDTO(selectedOrder);
            return new SelectedErrorStatus(0, "Order found", null,null,orderDTO);
        } else {
            return new SelectedErrorStatus(2, "Order not found");
        }
    }
    @Override
    public void deleteOrder(String orderId) {
        Optional<OrderEntity> existedOrder = orderDao.findById(orderId);
        if (!existedOrder.isPresent()) {
            throw new OrderNotFoundException("Order not found");
        } else {
            orderDao.deleteById(orderId);
        }
    }
    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderEntity> allOrders = orderDao.findAll();
        return mapping.asOrderDtoList(allOrders);
    }
    @Override
    public void updateOrder(String orderId, OrderDTO orderDTO) {
        Optional<OrderEntity> tempOrder = orderDao.findById(orderId);
        if (tempOrder.isPresent()) {
            tempOrder.get().setTotal(orderDTO.getTotal());
            tempOrder.get().setDiscount(orderDTO.getDiscount());
            tempOrder.get().setNewTotal(orderDTO.getNewTotal());
            tempOrder.get().setPaidAmount(orderDTO.getPaidAmount());
            tempOrder.get().setBalance(orderDTO.getBalance());
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }
}
