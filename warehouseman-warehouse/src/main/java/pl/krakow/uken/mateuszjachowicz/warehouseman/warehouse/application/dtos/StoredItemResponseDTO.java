package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class StoredItemResponseDTO {
    private UUID id;
    private ItemSimpleDTO item;
    private WarehouseSimpleDTO warehouse;
    private int quantity;

    @Data
    public static class ItemSimpleDTO {
        private String code;
        private String name;
        private SupplierSimpleDTO supplier;
        private int price;
    }

    @Data
    public static class SupplierSimpleDTO {
        private String id;
        private String code;
    }

    @Data
    public static class WarehouseSimpleDTO {
        private String code;
        private String name;
        private String address1;
        private String address2;
        private String country;
    }
}
