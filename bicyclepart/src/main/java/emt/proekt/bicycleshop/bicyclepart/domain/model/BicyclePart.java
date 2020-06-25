package emt.proekt.bicycleshop.bicyclepart.domain.model;


import emt.proekt.bicycleshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import emt.proekt.bicycleshop.sharedkernel.domain.origin.Manufacturer;

import javax.persistence.*;

@Entity
@Table(name="bicyclepart")
public class BicyclePart extends AbstractEntity<BicyclePartId> {

    @EmbeddedId
    private BicyclePartId id;

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

    @Column(name="name", nullable = false)
    private String name;

    @Override
    public BicyclePartId id() {
        return id;
    }
}
