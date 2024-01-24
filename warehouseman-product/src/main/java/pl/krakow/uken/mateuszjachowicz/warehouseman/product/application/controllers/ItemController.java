package pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemListResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemSearchParamsDTO;

import java.util.UUID;

public interface ItemController {
    @PostMapping("/items")
    ResponseEntity<ItemResponseDTO> createItem(@Valid @RequestBody ItemCreateRequestDTO body);
    @GetMapping("/items/{id}")
    ResponseEntity<ItemResponseDTO> getItemByID(@Valid @PathVariable UUID id);
    @GetMapping("/items")
    ResponseEntity<ItemListResponseDTO> getItemsByParams(ItemSearchParamsDTO params);
}
