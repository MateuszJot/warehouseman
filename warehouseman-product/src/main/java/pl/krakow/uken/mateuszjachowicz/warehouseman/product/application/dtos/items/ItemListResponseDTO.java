package pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class ItemListResponseDTO {
    private List<ItemResponseDTO> items;
    int totalPages;

    long totalElements;
}
