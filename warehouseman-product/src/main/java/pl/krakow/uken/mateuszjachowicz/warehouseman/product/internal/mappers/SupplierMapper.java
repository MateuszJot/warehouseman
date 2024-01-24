package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.entites.SupplierEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.mappers.MainMapperConfig;

@Mapper(config = MainMapperConfig.class)
public interface SupplierMapper {
    SupplierResponseDTO toResponse(SupplierEntity entity);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    SupplierEntity toEntity(SupplierCreateRequestDTO dto);
}
