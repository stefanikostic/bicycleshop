package emt.proekt.bicycleshop.order.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="order_items")
public class OrderItem extends AbstractEntity<OrderItemId> {

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="product_id",nullable = false))
    private ProductId productId;

    @Embedded
    private Money itemPrice;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    private OrderItem() {

    }

    public OrderItem(ProductId id, Money itemPrice, int quantity) {
        super(DomainObjectId.randomId(OrderItemId.class));

        this.productId = id;
        this.itemPrice = itemPrice;
        if(quantity<0){
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }
        this.quantity = quantity;
    }

    public void setItemPrice(Money itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setQuantity(int quantity) {
        if(quantity<0){
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }
        this.quantity = quantity;
    }

    @Override
    public OrderItemId id() {
        return id;
    }

    public Money subtotal() {
        return itemPrice.multiply(quantity);
    }
}
