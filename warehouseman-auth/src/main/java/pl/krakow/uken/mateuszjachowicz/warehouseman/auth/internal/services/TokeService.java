package pl.krakow.uken.mateuszjachowicz.warehouseman.auth.internal.services;

import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenVerifyRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenVerifyResponseDTO;

public interface TokeService {
    TokenResponseDTO getToken();

    TokenVerifyResponseDTO verifyToken(TokenVerifyRequestDTO requestDTO);
}
