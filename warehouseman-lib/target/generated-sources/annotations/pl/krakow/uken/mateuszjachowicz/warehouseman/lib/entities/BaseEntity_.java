package pl.krakow.uken.mateuszjachowicz.warehouseman.lib.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(BaseEntity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class BaseEntity_ {

	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.lib.entities.BaseEntity#id
	 **/
	public static volatile SingularAttribute<BaseEntity, UUID> id;
	
	/**
	 * @see pl.krakow.uken.mateuszjachowicz.warehouseman.lib.entities.BaseEntity
	 **/
	public static volatile MappedSuperclassType<BaseEntity> class_;

	public static final String ID = "id";

}

