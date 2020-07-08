package emt.proekt.bicycleshop.order.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.Type;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import emt.proekt.bicycleshop.sharedkernel.domain.origin.Manufacturer;
import lombok.Getter;

@Getter
public class Product {

    private ProductId id;

    private String productModel;

    private Money productPrice;

    private int yearManufactured;

    private int quantity;

    private Manufacturer manufacturer;

    private String description;

    private Type type;

}
