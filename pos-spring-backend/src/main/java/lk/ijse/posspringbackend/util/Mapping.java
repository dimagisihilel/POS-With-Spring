package lk.ijse.posspringbackend.util;

import lk.ijse.posspringbackend.dto.impl.CustomerDTO;
import lk.ijse.posspringbackend.dto.impl.ItemDTO;
import lk.ijse.posspringbackend.dto.impl.OrderDTO;
import lk.ijse.posspringbackend.dto.impl.OrderDetailDTO;
import lk.ijse.posspringbackend.entity.impl.CustomerEntity;
import lk.ijse.posspringbackend.entity.impl.ItemEntity;
import lk.ijse.posspringbackend.entity.impl.OrderDetailEntity;
import lk.ijse.posspringbackend.entity.impl.OrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    // For Customer mapping
    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }
    public CustomerDTO toCustomerDTO(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }
    public List<CustomerDTO> asCustomerDtoList(List<CustomerEntity> customerEntities) {
        return modelMapper.map(customerEntities, new TypeToken<List<CustomerDTO>>() {}.getType());
    }

    // For Item mapping
    public ItemEntity toItemEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, ItemEntity.class);
    }
    public ItemDTO toItemDTO(ItemEntity itemEntity) {
        return modelMapper.map(itemEntity, ItemDTO.class);
    }
    public List<ItemDTO> asItemDtoList(List<ItemEntity> itemEntities) {
        return modelMapper.map(itemEntities, new TypeToken<List<ItemDTO>>() {}.getType());
    }

    // For Order mapping
    public OrderEntity toOrderEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, OrderEntity.class);
    }

    public OrderDTO toOrderDTO(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, OrderDTO.class);
    }

    public List<OrderDTO> asOrderDtoList(List<OrderEntity> orderEntities) {
        return modelMapper.map(orderEntities, new TypeToken<List<OrderDTO>>() {}.getType());
    }

    // For OrderDetail mapping
    public OrderDetailEntity toOrderDetailEntity(OrderDetailDTO orderDetailDTO) {
        return modelMapper.map(orderDetailDTO, OrderDetailEntity.class);
    }
    public OrderDetailDTO toOrderDetailDTO(OrderDetailEntity orderDetailEntity) {
        return modelMapper.map(orderDetailEntity, OrderDetailDTO.class);
    }
    public List<OrderDetailDTO> asOrderDetailDtoList(List<OrderDetailEntity> orderDetailEntities) {
        return modelMapper.map(orderDetailEntities, new TypeToken<List<OrderDetailDTO>>() {}.getType());
    }
}
