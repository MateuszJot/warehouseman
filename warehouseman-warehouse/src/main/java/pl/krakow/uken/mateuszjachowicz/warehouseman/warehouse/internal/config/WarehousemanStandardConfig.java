package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.config.OriginFilter;

@RequiredArgsConstructor
@Configuration
public class WarehousemanStandardConfig implements WebMvcConfigurer {

    private final MyAuthFilter myAuthFilter;

    // origin filter first
    // then auth filter
    private static final int ORDER_ORIGIN_FILTER = SecurityProperties.DEFAULT_FILTER_ORDER-1;
    private static final int ORDER_AUTH_FILTER = SecurityProperties.DEFAULT_FILTER_ORDER;

    @Bean
    public FilterRegistrationBean<MyAuthFilter> authFilter() {
        FilterRegistrationBean<MyAuthFilter> frb = new FilterRegistrationBean<>(myAuthFilter);
        frb.setOrder(ORDER_AUTH_FILTER);
        frb.setEnabled(true);
        return frb;
    }

    @Bean
    public FilterRegistrationBean<OriginFilter> originFilter() {
        FilterRegistrationBean<OriginFilter> frb = new FilterRegistrationBean<>(new OriginFilter());
        frb.setOrder(ORDER_ORIGIN_FILTER);
        frb.setEnabled(true);
        return frb;
    }
}
