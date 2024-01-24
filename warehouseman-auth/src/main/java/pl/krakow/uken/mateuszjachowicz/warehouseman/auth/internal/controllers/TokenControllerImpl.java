package pl.krakow.uken.mateuszjachowicz.warehouseman.auth.internal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.controllers.TokenController;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenVerifyRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenVerifyResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.internal.services.TokeService;

@RequiredArgsConstructor
@RestController
public class TokenControllerImpl implements TokenController {
    private final TokeService service;
    @Override
    public ResponseEntity<TokenResponseDTO> getToken() {
        return ResponseEntity.ok(service.getToken());
    }

    @Override
    public ResponseEntity<TokenVerifyResponseDTO> verifyToken(TokenVerifyRequestDTO requestDTO) {
        return ResponseEntity.ok(service.verifyToken(requestDTO));
    }
}
