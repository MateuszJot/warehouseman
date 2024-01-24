package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.entities.BaseEntity;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="ItemCloned")
public class ItemClonedEntity extends BaseEntity {
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private UUID supplierID;
    @Column
    private String supplierCode;
    @Column
    private int price;
}
