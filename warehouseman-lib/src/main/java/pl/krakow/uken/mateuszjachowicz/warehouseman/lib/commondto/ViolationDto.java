package pl.krakow.uken.mateuszjachowicz.warehouseman.lib.commondto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ViolationDto {
    private String fieldName;
    private String message;
}
