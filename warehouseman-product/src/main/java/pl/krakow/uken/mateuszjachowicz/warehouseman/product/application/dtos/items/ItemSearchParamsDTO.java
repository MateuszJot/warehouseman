package pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items;

import lombok.Data;

@Data
public class ItemSearchParamsDTO {
    private String searchString;
    private Integer priceMin;
    private Integer priceMax;
    private int pageNumber;
    private int pageSize;
}
