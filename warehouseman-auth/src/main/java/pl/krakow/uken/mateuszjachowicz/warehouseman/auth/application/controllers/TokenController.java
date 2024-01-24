package pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenVerifyRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenVerifyResponseDTO;

public interface TokenController {
    @GetMapping("/token")
    ResponseEntity<TokenResponseDTO> getToken();

    @PostMapping("/token/verify")
    ResponseEntity<TokenVerifyResponseDTO> verifyToken(@RequestBody TokenVerifyRequestDTO requestDTO);
}
