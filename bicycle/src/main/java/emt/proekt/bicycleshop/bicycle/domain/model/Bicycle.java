package emt.proekt.bicycleshop.bicycle.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import emt.proekt.bicycleshop.sharedkernel.domain.origin.Manufacturer;
import lombok.Getter;


import javax.persistence.*;

@Entity
@Getter
@Table(name="bicycle")
public class Bicycle extends AbstractEntity<BicycleId> {

    @EmbeddedId
    private BicycleId id;

    @Version
    private Long version;

    @Column(name="model", nullable = false)
    private String model;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Money price;

    private int yearManufacture;

    private int quantity;

    private Manufacturer manufacturer;

    private String description;

    private boolean deleted = false;

    private String color;

    private String type;

    @Override
    public BicycleId id() {
        return id;
    }
}
