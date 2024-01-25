package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.services;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.*;

import java.util.UUID;

public interface WarehouseService {
    WarehouseResponseDTO createWarehouse(WarehouseCreateRequestDTO body);
    WarehouseResponseDTO getWarehouseByID(UUID id);
    StoredItemResponseDTO createWarehouseItemByID(UUID warehouseID, StoredItemCreateRequestDTO body);
    StoredItemResponseDTO getWarehouseItemByID(UUID warehouseID, UUID storedItemID);
    WarehouseListResponseDTO getSuppliers();
}
