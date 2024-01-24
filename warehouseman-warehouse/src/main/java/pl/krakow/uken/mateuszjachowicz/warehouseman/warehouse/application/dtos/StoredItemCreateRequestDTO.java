package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class StoredItemCreateRequestDTO {
    @NotNull
    private UUID item;
    private Integer quantity;
}
