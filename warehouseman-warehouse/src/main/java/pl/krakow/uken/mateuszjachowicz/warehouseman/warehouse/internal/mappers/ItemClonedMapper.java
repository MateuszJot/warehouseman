package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.mappers.MainMapperConfig;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity;

@Mapper(config = MainMapperConfig.class)
public interface ItemClonedMapper {

    @Mapping(target = "supplierID", source = "supplier.id")
    @Mapping(target = "supplierCode", source = "supplier.code")
    ItemClonedEntity toEntity(ItemResponseDTO entity);

    ItemClonedEntity updateEntity(ItemClonedEntity data, @MappingTarget ItemClonedEntity entityToUpdate);
}
