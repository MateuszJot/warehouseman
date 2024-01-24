package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.WarehouseEntity;

import java.util.List;
import java.util.UUID;
@Repository
public interface WarehouseRepository extends CrudRepository<WarehouseEntity, UUID>  {
    @Override
    List<WarehouseEntity> findAll();
}
