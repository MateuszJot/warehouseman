package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(ItemClonedEntity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ItemClonedEntity_ extends pl.krakow.uken.mateuszjachowicz.warehouseman.lib.entities.BaseEntity_ {

	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity#code
	 **/
	public static volatile SingularAttribute<ItemClonedEntity, String> code;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity#supplierID
	 **/
	public static volatile SingularAttribute<ItemClonedEntity, UUID> supplierID;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity#price
	 **/
	public static volatile SingularAttribute<ItemClonedEntity, Integer> price;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity#name
	 **/
	public static volatile SingularAttribute<ItemClonedEntity, String> name;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity#supplierCode
	 **/
	public static volatile SingularAttribute<ItemClonedEntity, String> supplierCode;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.ItemClonedEntity
	 **/
	public static volatile EntityType<ItemClonedEntity> class_;

	public static final String CODE = "code";
	public static final String SUPPLIER_ID = "supplierID";
	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String SUPPLIER_CODE = "supplierCode";

}

