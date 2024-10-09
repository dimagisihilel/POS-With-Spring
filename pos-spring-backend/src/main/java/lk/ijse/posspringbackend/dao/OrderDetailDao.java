package lk.ijse.posspringbackend.dao;

import lk.ijse.posspringbackend.entity.impl.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetailEntity, Long> {
}
