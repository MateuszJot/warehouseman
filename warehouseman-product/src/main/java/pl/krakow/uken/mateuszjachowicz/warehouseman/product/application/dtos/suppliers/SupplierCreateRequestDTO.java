package pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierCreateRequestDTO {
    private String code;
    private String name;
    private String address1;
    private String address2;
    private String country;
}