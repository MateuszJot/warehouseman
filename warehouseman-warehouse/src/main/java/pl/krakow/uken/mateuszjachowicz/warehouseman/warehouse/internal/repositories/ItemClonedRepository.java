package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity;

import java.util.UUID;

@Repository
public interface ItemClonedRepository extends CrudRepository<ItemClonedEntity, UUID> {
}
