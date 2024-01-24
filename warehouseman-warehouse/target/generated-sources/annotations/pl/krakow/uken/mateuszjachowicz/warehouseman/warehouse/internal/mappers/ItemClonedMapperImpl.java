package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.mappers;

import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.SupplierSimpleDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-23T21:39:06+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ItemClonedMapperImpl implements ItemClonedMapper {

    @Override
    public ItemClonedEntity toEntity(ItemResponseDTO entity) {
        if ( entity == null ) {
            return null;
        }

        ItemClonedEntity itemClonedEntity = new ItemClonedEntity();

        itemClonedEntity.setSupplierID( entitySupplierId( entity ) );
        itemClonedEntity.setSupplierCode( entitySupplierCode( entity ) );
        itemClonedEntity.setId( entity.getId() );
        itemClonedEntity.setCode( entity.getCode() );
        itemClonedEntity.setName( entity.getName() );
        itemClonedEntity.setPrice( entity.getPrice() );

        return itemClonedEntity;
    }

    @Override
    public ItemClonedEntity updateEntity(ItemClonedEntity data, ItemClonedEntity entityToUpdate) {
        if ( data == null ) {
            return entityToUpdate;
        }

        entityToUpdate.setId( data.getId() );
        entityToUpdate.setCode( data.getCode() );
        entityToUpdate.setName( data.getName() );
        entityToUpdate.setSupplierID( data.getSupplierID() );
        entityToUpdate.setSupplierCode( data.getSupplierCode() );
        entityToUpdate.setPrice( data.getPrice() );

        return entityToUpdate;
    }

    private UUID entitySupplierId(ItemResponseDTO itemResponseDTO) {
        if ( itemResponseDTO == null ) {
            return null;
        }
        SupplierSimpleDTO supplier = itemResponseDTO.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        UUID id = supplier.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entitySupplierCode(ItemResponseDTO itemResponseDTO) {
        if ( itemResponseDTO == null ) {
            return null;
        }
        SupplierSimpleDTO supplier = itemResponseDTO.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        String code = supplier.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }
}
