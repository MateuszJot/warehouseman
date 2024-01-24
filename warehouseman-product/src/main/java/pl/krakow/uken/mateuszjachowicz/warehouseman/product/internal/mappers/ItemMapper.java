package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemListResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.entites.ItemEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.mappers.MainMapperConfig;

@Mapper(config = MainMapperConfig.class)
public interface ItemMapper {
    ItemResponseDTO toResponse(ItemEntity entity);
    default ItemListResponseDTO toResponse(Page<ItemEntity> entities) {
        return new ItemListResponseDTO()
                .setItems(entities.map(this::toResponse).getContent())
                .setTotalElements(entities.getTotalElements())
                .setTotalPages(entities.getTotalPages());
    }
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "id", ignore = true)
    ItemEntity toEntity(ItemCreateRequestDTO dto);
}
