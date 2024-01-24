package pl.krakow.uken.mateuszjachowicz.warehouseman.auth.internal.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.config.OriginFilter;

@Configuration
public class WarehousemanStandardConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean originFilter() {
        FilterRegistrationBean frb = new FilterRegistrationBean(new OriginFilter());
        frb.setEnabled(true);
        return frb;
    }
}
