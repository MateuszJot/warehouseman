package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.entites.SupplierEntity;

import java.util.UUID;
@Repository
public interface SupplierRepository extends CrudRepository<SupplierEntity, UUID> {

}
