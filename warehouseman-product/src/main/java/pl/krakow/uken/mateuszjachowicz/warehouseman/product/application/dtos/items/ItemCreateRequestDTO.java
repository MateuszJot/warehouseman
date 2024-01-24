package pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;
@Data
public class ItemCreateRequestDTO {
    @NotNull
    @Size(min=1, max=10)
    private String code;
    @NotNull
    @Size(min=3, max=20)
    private String name;
    @NotNull
    @Size(min=1, max=100)
    private String description;
    @NotNull
    private UUID supplier;
    @Min(1)
    @Max(Integer.MAX_VALUE)
    private int price;
    private boolean warehouseable;
}
