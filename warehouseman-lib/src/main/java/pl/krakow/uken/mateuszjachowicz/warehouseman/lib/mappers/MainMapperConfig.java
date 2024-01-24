package pl.krakow.uken.mateuszjachowicz.warehouseman.lib.mappers;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MainMapperConfig {
}
