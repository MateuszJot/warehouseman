package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.services;

import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity;

import java.util.UUID;

public interface ItemClonedService {

    ItemClonedEntity getById(UUID id);
}
