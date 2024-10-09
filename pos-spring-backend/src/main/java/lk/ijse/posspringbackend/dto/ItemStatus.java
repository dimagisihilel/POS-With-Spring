package lk.ijse.posspringbackend.dto;

import lk.ijse.posspringbackend.dto.impl.ItemDTO;

import java.io.Serializable;

public interface ItemStatus extends Serializable,SuperDTO {
    ItemDTO getItemDTO();
}
