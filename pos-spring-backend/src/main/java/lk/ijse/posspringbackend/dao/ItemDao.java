package lk.ijse.posspringbackend.dao;

import lk.ijse.posspringbackend.entity.impl.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<ItemEntity,String> {
}
