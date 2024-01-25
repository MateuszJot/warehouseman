package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.controllers.WarehouseController;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.*;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.services.WarehouseService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class WarehouseControllerImpl implements WarehouseController {

    private final WarehouseService service;

    @Override
    public ResponseEntity<WarehouseResponseDTO> createWarehouse(WarehouseCreateRequestDTO body) {
        return ResponseEntity.ok(service.createWarehouse(body));
    }

    @Override
    public ResponseEntity<WarehouseResponseDTO> getWarehouseByID(UUID id) {
        return ResponseEntity.ok(service.getWarehouseByID(id));
    }

    @Override
    public ResponseEntity<StoredItemResponseDTO> createWarehouseItemByID(UUID warehouseID, StoredItemCreateRequestDTO body) {
        return ResponseEntity.ok(service.createWarehouseItemByID(warehouseID, body));
    }

    @Override
    public ResponseEntity<StoredItemResponseDTO> getWarehouseItemByID(UUID warehouseID, UUID storedItemID) {
        return ResponseEntity.ok(service.getWarehouseItemByID(warehouseID, storedItemID));
    }

    @Override
    public ResponseEntity<WarehouseListResponseDTO> getWarehouses() {
        return ResponseEntity.ok(service.getSuppliers());
    }
}
