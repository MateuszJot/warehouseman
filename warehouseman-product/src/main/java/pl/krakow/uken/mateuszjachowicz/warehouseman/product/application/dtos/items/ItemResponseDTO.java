package pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items;

import lombok.Data;

import java.util.UUID;
@Data
public class ItemResponseDTO {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private SupplierSimpleDTO supplier;
    private int price;
    private boolean warehouseable;
}