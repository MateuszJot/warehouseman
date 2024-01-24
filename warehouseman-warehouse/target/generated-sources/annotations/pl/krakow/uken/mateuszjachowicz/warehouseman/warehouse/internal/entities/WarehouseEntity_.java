package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(WarehouseEntity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class WarehouseEntity_ extends pl.krakow.uken.mateuszjachowicz.warehouseman.lib.entities.BaseEntity_ {

	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.WarehouseEntity#country
	 **/
	public static volatile SingularAttribute<WarehouseEntity, String> country;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.WarehouseEntity#code
	 **/
	public static volatile SingularAttribute<WarehouseEntity, String> code;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.WarehouseEntity#address2
	 **/
	public static volatile SingularAttribute<WarehouseEntity, String> address2;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.WarehouseEntity#address1
	 **/
	public static volatile SingularAttribute<WarehouseEntity, String> address1;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.WarehouseEntity#name
	 **/
	public static volatile SingularAttribute<WarehouseEntity, String> name;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities.WarehouseEntity
	 **/
	public static volatile EntityType<WarehouseEntity> class_;

	public static final String COUNTRY = "country";
	public static final String CODE = "code";
	public static final String ADDRESS2 = "address2";
	public static final String ADDRESS1 = "address1";
	public static final String NAME = "name";

}

