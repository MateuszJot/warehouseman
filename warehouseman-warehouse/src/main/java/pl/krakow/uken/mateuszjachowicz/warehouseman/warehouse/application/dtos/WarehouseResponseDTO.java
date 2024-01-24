package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos;


import lombok.Data;

import java.util.UUID;

@Data
public class WarehouseResponseDTO {
    private UUID id;
    private String code;
    private String name;
    private String address1;
    private String address2;
    private String country;
}
