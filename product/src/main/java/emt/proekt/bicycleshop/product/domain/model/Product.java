package emt.proekt.bicycleshop.product.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.bicycleshop.sharedkernel.domain.base.Type;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import emt.proekt.bicycleshop.sharedkernel.domain.origin.Manufacturer;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Table(name="products")
public class Product extends AbstractEntity<ProductId> {
    @Version
    private Long version;

    @Column(name="product_model", nullable = false)
    private String productModel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Money productPrice;

    @Column(name="year_manufactured", nullable = false)
    private int yearManufactured;

    private int quantity;

    private Manufacturer manufacturer;

    private String description;

    @Column(name="type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Type type;

    private boolean deleted = false;

    public Product() {
    }
    public Product(ProductId id, String productModel, Money productPrice,
                   int quantity, int yearManufactured,Manufacturer manufacturer,String description, Type type) {
        super(id);
        this.productModel = productModel;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.yearManufactured = yearManufactured;
        this.manufacturer = manufacturer;
        this.description = description;
        this.type = type;
    }

    public void subtractQuantity(int quantity){
        if(quantity>this.quantity){
            throw new RuntimeException("unsupported quantity");
        }
        this.quantity -= quantity;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return yearManufactured == product.yearManufactured &&
                quantity == product.quantity &&
                deleted == product.deleted &&
                Objects.equals(version, product.version) &&
                productModel.equals(product.productModel) &&
                productPrice.equals(product.productPrice) &&
                manufacturer.equals(product.manufacturer) &&
                Objects.equals(description, product.description);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                '}';
    }

    @Override
    public ProductId id() {
        return id;
    }
}
