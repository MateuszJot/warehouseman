package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.controllers.ItemController;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemListResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemSearchParamsDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.services.ItemService;

import java.util.UUID;
@RequiredArgsConstructor
@RestController
public class ItemControllerImpl implements ItemController {
    private final ItemService service;
    @Override
    public ResponseEntity<ItemResponseDTO> createItem(ItemCreateRequestDTO body) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createItem(body));
    }
    @Override
    public ResponseEntity<ItemResponseDTO> getItemByID(@Valid @PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getItemByID(id));
    }

    @Override
    public ResponseEntity<ItemListResponseDTO> getItemsByParams(ItemSearchParamsDTO params) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getItemsByParams(params));
    }
}
