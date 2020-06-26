package emt.proekt.bicycleshop.bicyclepart.domain.model;


import emt.proekt.bicycleshop.sharedkernel.domain.base.Product;
import emt.proekt.bicycleshop.sharedkernel.domain.base.ProductId;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import emt.proekt.bicycleshop.sharedkernel.domain.origin.Manufacturer;

import javax.persistence.*;

@Entity
@Table(name="bicycle_part")
public class BicyclePart extends Product {

    @Column(name="part_name", nullable = false)
    private String partName;


    public BicyclePart(ProductId productId, String partName, String productModel, Money productPrice, int quantity,
                       int yearManufactured, Manufacturer manufacturer, String description) {
        super(productId,productModel, productPrice, quantity, yearManufactured,manufacturer,description);
        this.partName = partName;

    }

    @Override
    public ProductId id() {
        return id;
    }
}
