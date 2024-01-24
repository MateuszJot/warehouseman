package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.entities.BaseEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Supplier")
public class SupplierEntity extends BaseEntity {
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
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private List<ItemEntity> items;
}
