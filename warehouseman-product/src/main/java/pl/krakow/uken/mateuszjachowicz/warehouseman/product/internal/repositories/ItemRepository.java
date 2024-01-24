package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.entites.ItemEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, UUID>, JpaSpecificationExecutor<ItemEntity> {
    Optional<ItemEntity> findByCode(String code);
    @Override
    List<ItemEntity> findAll();
    @Query("SELECT i FROM ItemEntity i WHERE i.supplier.code = :code")
    List<ItemEntity> findBySupplier(@Param("code") String code);
}
