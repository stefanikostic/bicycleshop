package emt.proekt.bicycleshop.bicyclepart.domain.model;


import emt.proekt.bicycleshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.bicycleshop.sharedkernel.domain.base.Product;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import emt.proekt.bicycleshop.sharedkernel.domain.origin.Manufacturer;

import javax.persistence.*;

@Entity
@Table(name="bicyclepart")
public class BicyclePart extends Product<BicyclePartId> {

    @EmbeddedId
    private BicyclePartId id;

    private String color;


    @Override
    public BicyclePartId id() {
        return id;
    }
}
