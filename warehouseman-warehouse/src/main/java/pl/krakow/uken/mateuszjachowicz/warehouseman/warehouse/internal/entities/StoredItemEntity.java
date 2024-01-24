package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.entities.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="StoredItem")
public class StoredItemEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="item_id")
    private ItemClonedEntity item;
    @ManyToOne
    @JoinColumn(name="warehouse_id")
    private WarehouseEntity warehouse;
    @Column
    private int quantity;
}
