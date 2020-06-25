package emt.proekt.bicycleshop.order.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name="order_items")
public class OrderItem extends AbstractEntity<OrderItemId> {

    @Embedded
    private DomainObjectId productId;

    @Embedded
    private Money itemPrice;

    @Column(name = "qty", nullable = false)
    private int quantity;

    private OrderItem() {

    }

    public OrderItem(DomainObjectId id, Money itemPrice, int quantity) {
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
