package pl.krakow.uken.mateuszjachowicz.warehouseman.auth.internal.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenVerifyRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenVerifyResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.internal.config.JWTConfig;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokeServiceImpl implements TokeService {
    private final JWTConfig jwtConfig;

    @Override
    public TokenResponseDTO getToken() {
        return new TokenResponseDTO(generateToken());
    }

    @Override
    public TokenVerifyResponseDTO verifyToken(TokenVerifyRequestDTO requestDTO) {
        return new TokenVerifyResponseDTO(validateToken(requestDTO.getToken()));
    }

    private boolean validateToken(String token) {
        long now = System.currentTimeMillis();
        try {
            Claims payload = Jwts
                    .parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return payload.getExpiration().getTime() > now && payload.getIssuedAt().getTime() < now;
        }
        catch (RuntimeException e) {
            return false;
        }
    }

    public String generateToken() {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(now + jwtConfig.getExpiration()))
                .signWith(getKey())
                .compact();

    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretBytes());
    }
}
