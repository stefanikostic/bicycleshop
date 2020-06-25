package emt.proekt.bicycleshop.bicycle.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.bicycleshop.sharedkernel.domain.base.Product;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import emt.proekt.bicycleshop.sharedkernel.domain.origin.Manufacturer;
import lombok.Getter;


import javax.persistence.*;

@Entity
@Getter
@Table(name="bicycle")
public class Bicycle extends Product<BicycleId> {

    @EmbeddedId
    private BicycleId id;

    private String type;

    @Override
    public BicycleId id() {
        return id;
    }
}
