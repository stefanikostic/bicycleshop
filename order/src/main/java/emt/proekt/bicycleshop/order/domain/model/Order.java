package emt.proekt.bicycleshop.order.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import emt.proekt.bicycleshop.bicycle.domain.model.Bicycle;
import emt.proekt.bicycleshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.bicycleshop.sharedkernel.domain.base.Product;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Currency;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import lombok.Getter;
import lombok.NonNull;


import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    @EmbeddedId
    private OrderId id;

    @Version
    private Long version;

    @Column(name = "ordered_on", nullable = false)
    private Instant orderedOn;

    @Column(name = "order_currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "order_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "billing_name", nullable = false)),
            @AttributeOverride(name = "address", column = @Column(name = "billing_address", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country", nullable = false))
    })
    private RecipientAddress billingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> items;

    @SuppressWarnings("unused")
    private Order() {

    }



    public Order(@NonNull Instant orderedOn, @NonNull Currency currency, @NonNull RecipientAddress billingAddress) {
        super(DomainObjectId.randomId(OrderId.class));
        this.items = new HashSet<>();
        setCurrency(currency);
        setOrderedOn(orderedOn);
        setState(OrderState.RECEIVED);
        setBillingAddress(billingAddress);
    }

    @NonNull
    @JsonProperty("state")
    public OrderState state() {
        return state;
    }

    private void setState(@NonNull OrderState state) {
        this.state = state;
    }

    public void setOrderedOn(Instant orderedOn) {
        this.orderedOn = orderedOn;
    }

    public void setBillingAddress(RecipientAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Stream<OrderItem> getItems() {
        return items.stream();
    }

    public OrderItem addItem(@NonNull Product product, int qty) {
        Objects.requireNonNull(product, "product must not be null");
        var item = new OrderItem(product.getId(), product.getPrice(), qty);
        item.setQuantity(qty);
        items.add(item);
        return item;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Instant getOrderedOn() {
        return orderedOn;
    }

    public Currency getCurrency() {
        return currency;
    }

    public OrderState getState() {
        return state;
    }

    public RecipientAddress getBillingAddress() {
        return billingAddress;
    }

    public Money total() {
        return items.stream().map(OrderItem::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    @Override
    public OrderId id() {
        return id;
    }
}
