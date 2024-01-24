package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.StoredItemCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.StoredItemResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.WarehouseCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.WarehouseResponseDTO;

import java.util.UUID;

public interface WarehouseController {
    @PostMapping("/warehouses")
    ResponseEntity<WarehouseResponseDTO> createWarehouse(@Valid @RequestBody WarehouseCreateRequestDTO body);
    @GetMapping("/warehouses/{id}")
    ResponseEntity<WarehouseResponseDTO> getWarehouseByID(@Valid @PathVariable UUID id);
    @PostMapping("/warehouses/{warehouseID}/items")
    ResponseEntity<StoredItemResponseDTO> createWarehouseItemByID(@Valid @PathVariable UUID warehouseID, @Valid @RequestBody StoredItemCreateRequestDTO body);
    @GetMapping("/warehouses/{warehouseID}/items/{storedItemID}")
    ResponseEntity<StoredItemResponseDTO> getWarehouseItemByID(@Valid @PathVariable UUID warehouseID, @Valid @PathVariable UUID storedItemID);
}
