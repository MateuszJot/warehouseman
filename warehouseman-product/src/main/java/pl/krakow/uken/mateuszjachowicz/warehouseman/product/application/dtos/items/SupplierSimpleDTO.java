package pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items;

import lombok.Data;

import java.util.UUID;
@Data
public class SupplierSimpleDTO {
    private UUID id;
    private String name;
    private String code;
}
