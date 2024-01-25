package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.StoredItemCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.StoredItemResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.WarehouseCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.application.dtos.WarehouseResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.StoredItemEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.WarehouseEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-25T21:11:58+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class WarehouseMapperImpl implements WarehouseMapper {

    @Override
    public WarehouseResponseDTO toResponse(WarehouseEntity warehouseEntity) {
        if ( warehouseEntity == null ) {
            return null;
        }

        WarehouseResponseDTO warehouseResponseDTO = new WarehouseResponseDTO();

        warehouseResponseDTO.setId( warehouseEntity.getId() );
        warehouseResponseDTO.setCode( warehouseEntity.getCode() );
        warehouseResponseDTO.setName( warehouseEntity.getName() );
        warehouseResponseDTO.setAddress1( warehouseEntity.getAddress1() );
        warehouseResponseDTO.setAddress2( warehouseEntity.getAddress2() );
        warehouseResponseDTO.setCountry( warehouseEntity.getCountry() );

        return warehouseResponseDTO;
    }

    @Override
    public StoredItemResponseDTO toResponse(StoredItemEntity warehouseEntity) {
        if ( warehouseEntity == null ) {
            return null;
        }

        StoredItemResponseDTO storedItemResponseDTO = new StoredItemResponseDTO();

        storedItemResponseDTO.setId( warehouseEntity.getId() );
        storedItemResponseDTO.setItem( toSimpleDTO( warehouseEntity.getItem() ) );
        storedItemResponseDTO.setWarehouse( warehouseEntityToWarehouseSimpleDTO( warehouseEntity.getWarehouse() ) );
        storedItemResponseDTO.setQuantity( warehouseEntity.getQuantity() );

        return storedItemResponseDTO;
    }

    @Override
    public StoredItemResponseDTO.ItemSimpleDTO toSimpleDTO(ItemClonedEntity entity) {
        if ( entity == null ) {
            return null;
        }

        StoredItemResponseDTO.ItemSimpleDTO itemSimpleDTO = new StoredItemResponseDTO.ItemSimpleDTO();

        itemSimpleDTO.setSupplier( itemClonedEntityToSupplierSimpleDTO( entity ) );
        itemSimpleDTO.setCode( entity.getCode() );
        itemSimpleDTO.setName( entity.getName() );
        itemSimpleDTO.setPrice( entity.getPrice() );

        return itemSimpleDTO;
    }

    @Override
    public WarehouseEntity toEntity(WarehouseCreateRequestDTO body) {
        if ( body == null ) {
            return null;
        }

        WarehouseEntity warehouseEntity = new WarehouseEntity();

        warehouseEntity.setCode( body.getCode() );
        warehouseEntity.setName( body.getName() );
        warehouseEntity.setAddress1( body.getAddress1() );
        warehouseEntity.setAddress2( body.getAddress2() );
        warehouseEntity.setCountry( body.getCountry() );

        return warehouseEntity;
    }

    @Override
    public StoredItemEntity toEntity(StoredItemCreateRequestDTO body) {
        if ( body == null ) {
            return null;
        }

        StoredItemEntity storedItemEntity = new StoredItemEntity();

        if ( body.getQuantity() != null ) {
            storedItemEntity.setQuantity( body.getQuantity() );
        }

        return storedItemEntity;
    }

    protected StoredItemResponseDTO.WarehouseSimpleDTO warehouseEntityToWarehouseSimpleDTO(WarehouseEntity warehouseEntity) {
        if ( warehouseEntity == null ) {
            return null;
        }

        StoredItemResponseDTO.WarehouseSimpleDTO warehouseSimpleDTO = new StoredItemResponseDTO.WarehouseSimpleDTO();

        warehouseSimpleDTO.setCode( warehouseEntity.getCode() );
        warehouseSimpleDTO.setName( warehouseEntity.getName() );
        warehouseSimpleDTO.setAddress1( warehouseEntity.getAddress1() );
        warehouseSimpleDTO.setAddress2( warehouseEntity.getAddress2() );
        warehouseSimpleDTO.setCountry( warehouseEntity.getCountry() );

        return warehouseSimpleDTO;
    }

    protected StoredItemResponseDTO.SupplierSimpleDTO itemClonedEntityToSupplierSimpleDTO(ItemClonedEntity itemClonedEntity) {
        if ( itemClonedEntity == null ) {
            return null;
        }

        StoredItemResponseDTO.SupplierSimpleDTO supplierSimpleDTO = new StoredItemResponseDTO.SupplierSimpleDTO();

        if ( itemClonedEntity.getSupplierID() != null ) {
            supplierSimpleDTO.setId( itemClonedEntity.getSupplierID().toString() );
        }
        supplierSimpleDTO.setCode( itemClonedEntity.getSupplierCode() );

        return supplierSimpleDTO;
    }
}
