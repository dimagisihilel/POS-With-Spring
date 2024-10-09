package lk.ijse.posspringbackend.service.impl;

import lk.ijse.posspringbackend.customStatusCode.SelectedErrorStatus;
import lk.ijse.posspringbackend.dto.ItemStatus;
import lk.ijse.posspringbackend.dto.impl.ItemDTO;
import lk.ijse.posspringbackend.entity.impl.ItemEntity;
import lk.ijse.posspringbackend.exception.DataPersistException;
import lk.ijse.posspringbackend.exception.ItemNotFoundException;
import lk.ijse.posspringbackend.service.ItemService;
import lk.ijse.posspringbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        ItemEntity savedItem = itemDao.save(mapping.toItemEntity(itemDTO));
        if (savedItem == null) {
            throw new DataPersistException("Failed to save item");
        }
    }
    @Override
    public List<ItemDTO> getAllItems() {
        List<ItemEntity> allItems = itemDao.findAll();
        return mapping.asItemDtoList(allItems);
    }
    @Override
    public ItemStatus getItem(String itemId) {
        if (itemDao.existsById(itemId)) {
            ItemEntity selectedItem = itemDao.getReferenceById(itemId);
            ItemDTO itemDTO = mapping.toItemDTO(selectedItem);
            return new SelectedErrorStatus(0, "Item found",null, itemDTO, null);
        } else {
            return new SelectedErrorStatus(2, "Item not found");
        }
    }
    @Override
    public void deleteItem(String itemId) {
        Optional<ItemEntity> existedItem = itemDao.findById(itemId);
        if (!existedItem.isPresent()) {
            throw new ItemNotFoundException("Item not found");
        } else {
            itemDao.deleteById(itemId);
        }
    }
    @Override
    public void updateItem(String itemId, ItemDTO itemDTO) {
        Optional<ItemEntity> tempItem = itemDao.findById(itemId);
        if (tempItem.isPresent()) {
            tempItem.get().setDescription(itemDTO.getDescription());
            tempItem.get().setUnitPrice(itemDTO.getUnitPrice());
            tempItem.get().setQtyOnHand(itemDTO.getQtyOnHand());
        } else {
            throw new ItemNotFoundException("Item not found");
        }
    }
}
