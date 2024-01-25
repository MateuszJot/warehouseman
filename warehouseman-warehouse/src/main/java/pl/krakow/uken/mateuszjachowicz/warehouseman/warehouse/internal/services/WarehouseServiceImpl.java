package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.exceptions.EntityNotFoundException;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.*;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.StoredItemEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.WarehouseEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.mappers.WarehouseMapper;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.repositories.StoredItemRepository;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.repositories.WarehouseRepository;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository repo;
    private final StoredItemRepository itemRepo;
    private final WarehouseMapper mapper;
    private final ItemClonedService itemClonedService;

    @Override
    public WarehouseResponseDTO createWarehouse(WarehouseCreateRequestDTO body) {
        WarehouseEntity entity = mapper.toEntity(body);
        WarehouseEntity persistEntity = repo.save(entity);
        return mapper.toResponse(persistEntity);
    }

    @Override
    public WarehouseResponseDTO getWarehouseByID(UUID id) {
        return repo.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(WarehouseEntity.class, id));
    }

    @Override
    public StoredItemResponseDTO createWarehouseItemByID(UUID warehouseID, StoredItemCreateRequestDTO body) {
        WarehouseEntity warehouseEntity =
                repo.findById(warehouseID)
                        .orElseThrow(() -> new EntityNotFoundException(WarehouseEntity.class, warehouseID));


        ItemClonedEntity itemClonedEntity = itemClonedService.getById(body.getItem());
        StoredItemEntity storedItemEntity = mapper.toEntity(body);
        storedItemEntity.setWarehouse(warehouseEntity);
        storedItemEntity.setItem(itemClonedEntity);
        StoredItemEntity persisted = itemRepo.save(storedItemEntity);
        return mapper.toResponse(persisted);
    }

    @Override
    public StoredItemResponseDTO getWarehouseItemByID(UUID warehouseID, UUID storedItemID) {
        return itemRepo
                .findById(storedItemID)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(StoredItemEntity.class, storedItemID));
    }

    @Override
    public WarehouseListResponseDTO getSuppliers() {
        return mapper.toResponse(repo.findAll());
    }
}
