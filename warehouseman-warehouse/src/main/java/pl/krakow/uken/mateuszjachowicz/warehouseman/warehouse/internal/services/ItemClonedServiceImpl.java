package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.services;

import feign.FeignException;
import feign.RetryableException;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.exceptions.EntityNotFoundException;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.client.ItemClient;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.mappers.ItemClonedMapper;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.mappers.ItemClonedMapperImpl;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.repositories.ItemClonedRepository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemClonedServiceImpl implements ItemClonedService {

    private final ItemClient itemClient;
    private final ItemClonedRepository itemClonedRepository;
    private final ItemClonedMapper mapper;

    @Override
    public ItemClonedEntity getById(UUID id) {
        return fetchOrDb(id)
                .orElseThrow(() -> new EntityNotFoundException(ItemClonedEntity.class, id));
    }

    private Optional<ItemClonedEntity> fetchOrDb(UUID id) {
        try {
            ItemResponseDTO originalItem = itemClient.getItemByID(id).getBody();
            if (!originalItem.isWarehouseable()) {
                return Optional.empty();
            }
            ItemClonedEntity entity = mapper.toEntity(originalItem);
            updateOrCreate(entity);
            return Optional.of(entity);
        }
        catch (FeignException.NotFound ex) {
            // TODO? remove from db?
            return Optional.empty();
        }
        catch (RetryableException exception) {
            return itemClonedRepository.findById(id);
        }
    }

    private void updateOrCreate(ItemClonedEntity entity) {
        itemClonedRepository.findById(entity.getId())
                .map(item -> mapper.updateEntity(entity, item))
                .or(() -> Optional.of(entity))
                .ifPresent(itemClonedRepository::save);
    }

}
