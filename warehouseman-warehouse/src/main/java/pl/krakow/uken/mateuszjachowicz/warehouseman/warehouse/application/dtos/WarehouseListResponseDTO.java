package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos;

import lombok.Data;

import java.util.List;

@Data
public class WarehouseListResponseDTO {
    private List<WarehouseResponseDTO> items;
}
