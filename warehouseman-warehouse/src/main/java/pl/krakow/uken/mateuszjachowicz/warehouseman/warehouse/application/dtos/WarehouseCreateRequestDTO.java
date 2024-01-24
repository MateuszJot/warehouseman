package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WarehouseCreateRequestDTO {
    @NotNull
    @Size(min=1, max=10)
    private String code;
    @NotNull
    @Size(min=3, max=20)
    private String name;
    @NotNull
    @Size(min=1, max=100)
    private String address1;
    @NotNull
    @Size(min=1, max=100)
    private String address2;
    @NotNull
    @Size(min=1, max=20)
    private String country;
}
