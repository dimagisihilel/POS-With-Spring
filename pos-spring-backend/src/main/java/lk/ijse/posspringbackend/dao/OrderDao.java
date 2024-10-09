package lk.ijse.posspringbackend.dao;

import lk.ijse.posspringbackend.entity.impl.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity, String> {
    @Query(value = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1", nativeQuery = true)
    String getLastOrderId();
}
