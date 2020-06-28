package emt.proekt.bicycleshop.order.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import emt.proekt.bicycleshop.bicycle.domain.model.Bicycle;
import emt.proekt.bicycleshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Currency;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import lombok.Getter;
import lombok.NonNull;


import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    @Version
    private Long version;

    @Column(name = "ordered_date", nullable = false)
    private Instant orderedDate;

    @Column(name = "order_currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "order_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState state;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "shipping_address", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "shipping_city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "shipping_country", nullable = false))
    })
    private ShippingAddress shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> items;

    @SuppressWarnings("unused")
    private Order() {

    }

    public Order(@NonNull Instant orderedDate, @NonNull Currency currency, @NonNull ShippingAddress shippingAddress) {
        super(DomainObjectId.randomId(OrderId.class));
        this.items = new HashSet<>();
        setCurrency(currency);
        setOrderedDate(orderedDate);
        setState(OrderState.PROCESSING);
        setShippingAddress(shippingAddress);
    }

    public void cancel(){
        this.state = OrderState.CANCELLED;
    }
    @NonNull
    @JsonProperty("state")
    public OrderState state() {
        return state;
    }

    private void setState(@NonNull OrderState state) {
        this.state = state;
    }

    public void setOrderedDate(Instant orderedDate) {
        this.orderedDate = orderedDate;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Stream<OrderItem> getItems() {
        return items.stream();
    }

    public OrderItem addItem(@NonNull Product product, int qty) {
        Objects.requireNonNull(product, "product must not be null");
        var item = new OrderItem(product.getId(), product.getProductPrice(), qty);
        item.setQuantity(qty);
        items.add(item);
        return item;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Instant getOrderedDate() {
        return orderedDate;
    }


    public Money total() {
        return items.stream().map(OrderItem::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    @Override
    public OrderId id() {
        return id;
    }
}
