package emt.proekt.bicycleshop.order.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.ProductId;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import lombok.Getter;

@Getter
public class Product {

    private ProductId id;

    private Money productPrice;

    private int quantity;
}
