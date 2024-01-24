package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(StoredItemEntity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class StoredItemEntity_ extends pl.krakow.uken.mateuszjachowicz.warehouseman.lib.entities.BaseEntity_ {

	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.StoredItemEntity#item
	 **/
	public static volatile SingularAttribute<StoredItemEntity, ItemClonedEntity> item;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.StoredItemEntity#quantity
	 **/
	public static volatile SingularAttribute<StoredItemEntity, Integer> quantity;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.StoredItemEntity#warehouse
	 **/
	public static volatile SingularAttribute<StoredItemEntity, WarehouseEntity> warehouse;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.StoredItemEntity
	 **/
	public static volatile EntityType<StoredItemEntity> class_;

	public static final String ITEM = "item";
	public static final String QUANTITY = "quantity";
	public static final String WAREHOUSE = "warehouse";

}

