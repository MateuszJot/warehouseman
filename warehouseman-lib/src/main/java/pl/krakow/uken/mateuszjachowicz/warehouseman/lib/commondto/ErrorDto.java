package pl.krakow.uken.mateuszjachowicz.warehouseman.lib.commondto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDto {
    private String message;
    private long timestamp;
}
