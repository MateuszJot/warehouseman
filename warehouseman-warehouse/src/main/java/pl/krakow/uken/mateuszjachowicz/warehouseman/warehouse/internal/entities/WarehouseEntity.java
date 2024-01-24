package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.entities.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Warehouse")
public class WarehouseEntity extends BaseEntity {
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String address1;
    @Column
    private String address2;
    @Column
    private String country;
}

