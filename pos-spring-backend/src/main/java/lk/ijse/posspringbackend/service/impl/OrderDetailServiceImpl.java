package lk.ijse.posspringbackend.service.impl;

import lk.ijse.posspringbackend.dao.OrderDao;
import lk.ijse.posspringbackend.dto.impl.OrderDetailDTO;
import lk.ijse.posspringbackend.entity.impl.OrderDetailEntity;
import lk.ijse.posspringbackend.entity.impl.OrderEntity;
import lk.ijse.posspringbackend.service.OrderDetailService;
import lk.ijse.posspringbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveOrderDetail(OrderDetailDTO orderDetailDTO) {
        // Check if the order exists
        OrderEntity orderEntity = orderDao.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Map DTO to Entity
        OrderDetailEntity orderDetailEntity = mapping.toOrderDetailEntity(orderDetailDTO);

        // Set the order to the order detail entity
        orderDetailEntity.setOrder(orderEntity);

        // Save the order detail to the database
        orderDetailDao.save(orderDetailEntity);
    }
}
