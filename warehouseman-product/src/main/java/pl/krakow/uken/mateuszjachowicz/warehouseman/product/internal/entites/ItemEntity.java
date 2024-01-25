package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.entities.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Item")
public class ItemEntity extends BaseEntity {
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String description;
    @JoinColumn(name="supplier_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private SupplierEntity supplier;
    @Column
    private int price;
    @Column
    private boolean warehouseable;
}
