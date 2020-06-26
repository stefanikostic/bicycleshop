package emt.proekt.bicycleshop.order.application.form;

import emt.proekt.bicycleshop.order.domain.model.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class OrderItemForm implements Serializable {

    @NotNull
    private Product product;

    @Min(1)
    private int quantity = 1;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
