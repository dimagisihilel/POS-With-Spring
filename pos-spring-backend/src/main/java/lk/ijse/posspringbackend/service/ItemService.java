package lk.ijse.posspringbackend.service;

import lk.ijse.posspringbackend.dto.ItemStatus;
import lk.ijse.posspringbackend.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    ItemStatus getItem(String itemId);
    void deleteItem(String itemId);
    List<ItemDTO> getAllItems();
    void updateItem(String itemId, ItemDTO itemDTO);

}
