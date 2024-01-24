package pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenVerifyRequestDTO {
    private String token;
}
