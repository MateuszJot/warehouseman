package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemListResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierListResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.entites.ItemEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.entites.SupplierEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.mappers.MainMapperConfig;

import java.util.List;

@Mapper(config = MainMapperConfig.class)
public interface SupplierMapper {
    SupplierResponseDTO toResponse(SupplierEntity entity);
    default SupplierListResponseDTO toResponse(List<SupplierEntity> entities) {
        return new SupplierListResponseDTO()
                .setItems(entities.stream().map(this::toResponse).toList());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    SupplierEntity toEntity(SupplierCreateRequestDTO dto);
}
