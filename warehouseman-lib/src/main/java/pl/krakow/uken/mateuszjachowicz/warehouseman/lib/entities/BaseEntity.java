package pl.krakow.uken.mateuszjachowicz.warehouseman.lib.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseEntity {
    @Id
    @Column
    @GenericGenerator(name = "IdGenerator", strategy = "pl.krakow.uken.mateuszjachowicz.warehouseman.lib.config.IdGenerator")
    @GeneratedValue(generator = "IdGenerator")
    private UUID id;
}
