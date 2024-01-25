package pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class SupplierListResponseDTO {
    private List<SupplierResponseDTO> items;
}
