package pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierResponseDTO {
    private UUID id;
    private String code;
    private String name;
    private String address1;
    private String address2;
    private String country;
}
