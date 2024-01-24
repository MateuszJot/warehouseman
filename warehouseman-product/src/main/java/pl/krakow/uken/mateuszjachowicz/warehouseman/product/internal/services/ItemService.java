package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.services;

import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemListResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemSearchParamsDTO;

import java.util.UUID;

public interface ItemService {
    ItemResponseDTO createItem(ItemCreateRequestDTO body);
    ItemResponseDTO getItemByID(UUID id);
    ItemListResponseDTO getItemsByParams(ItemSearchParamsDTO params);
}
