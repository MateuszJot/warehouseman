package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.config;

import feign.FeignException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenVerifyRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.dtos.TokenVerifyResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.clients.TokenClient;

import java.io.IOException;

@NoArgsConstructor
@Component
public class MyAuthFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private TokenClient tokenClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(AUTHORIZATION);
        if(!StringUtils.hasLength(authorization)) {
            response.setStatus(401);
            return;
        }

        if(!hasAccess(authorization)) {
            response.setStatus(403);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean hasAccess(String authorization) {
        try {
            TokenVerifyResponseDTO body = tokenClient.verifyToken(new TokenVerifyRequestDTO(authorization)).getBody();
            return body != null && body.isValid();
        }
        catch (FeignException e) {
            return false;
        }
    }
}
