package pl.krakow.uken.mateuszjachowicz.warehouseman.auth.internal.config;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class JWTConfig {

    @Value("${security.jwt.expiration:30000}")
    private int expiration;

    @Value("${security.jwt.secret}")
    private String secret;

    public byte[] getSecretBytes() {
        return secret.getBytes();
    }
}