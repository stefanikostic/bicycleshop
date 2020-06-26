package emt.proekt.bicycleshop.order.application.form;

import emt.proekt.bicycleshop.order.domain.model.ShippingAddress;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Currency;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderForm implements Serializable {

    @NotNull
    private Currency currency;

    @Valid
    @NotNull
    private ShippingAddressForm shippingAddress = new ShippingAddressForm();

    @Valid
    @NotEmpty
    private List<OrderItemForm> items = new ArrayList<>();

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public ShippingAddressForm getShippingAddress() {
        return shippingAddress;
    }

    public List<OrderItemForm> getItems() {
        return items;
    }
}
