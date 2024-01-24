package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.StoredItemCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.StoredItemResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.WarehouseCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.WarehouseResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.StoredItemEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.WarehouseEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.mappers.MainMapperConfig;

@Mapper(config = MainMapperConfig.class)
public interface WarehouseMapper {
    WarehouseResponseDTO toResponse(WarehouseEntity warehouseEntity);
    StoredItemResponseDTO toResponse(StoredItemEntity warehouseEntity);

    @Mapping(target = "supplier.id", source = "supplierID")
    @Mapping(target = "supplier.code", source = "supplierCode")
    StoredItemResponseDTO.ItemSimpleDTO toSimpleDTO(ItemClonedEntity entity);

    @Mapping(target = "id", ignore = true)
    WarehouseEntity toEntity(WarehouseCreateRequestDTO body);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "warehouse", ignore = true)
    StoredItemEntity toEntity(StoredItemCreateRequestDTO body);
}
