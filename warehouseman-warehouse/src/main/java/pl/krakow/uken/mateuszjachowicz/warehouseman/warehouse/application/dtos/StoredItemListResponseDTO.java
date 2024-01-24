package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos;

import lombok.Data;

import java.util.List;
@Data
public class StoredItemListResponseDTO {
    private List<StoredItemResponseDTO> items;
}
