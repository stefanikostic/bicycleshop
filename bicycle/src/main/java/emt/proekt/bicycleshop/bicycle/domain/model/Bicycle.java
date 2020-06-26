package emt.proekt.bicycleshop.bicycle.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.bicycleshop.sharedkernel.domain.base.Product;
import emt.proekt.bicycleshop.sharedkernel.domain.base.ProductId;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import emt.proekt.bicycleshop.sharedkernel.domain.origin.Manufacturer;
import lombok.Getter;


import javax.persistence.*;

@Entity
@Getter
@Table(name="bicycle")
public class Bicycle extends Product {

    private String color;

    private String bicycleType;

    public Bicycle(ProductId productId, String color, String bicycleType, String productModel, Money productPrice, int quantity,
                       int yearManufactured, Manufacturer manufacturer, String description) {
        super(productId,productModel, productPrice, quantity, yearManufactured,manufacturer,description);
        this.color = color;
        this.bicycleType = bicycleType;
    }

    @Override
    public ProductId id() {
        return id;
    }
}
