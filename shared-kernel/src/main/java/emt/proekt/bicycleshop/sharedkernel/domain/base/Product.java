package emt.proekt.bicycleshop.sharedkernel.domain.base;


import emt.proekt.bicycleshop.sharedkernel.domain.financial.Money;
import emt.proekt.bicycleshop.sharedkernel.domain.origin.Manufacturer;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@MappedSuperclass
public abstract class Product<ID extends DomainObjectId> implements IdentifyableDomainObject<ID> {

    @Id
    protected ID id;

    @Version
    private Long version;

    @Column(name="name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Money price;

    private int yearManufacture;

    private int quantity;

    private Manufacturer manufacturer;

    private String description;

    private boolean deleted = false;

    public Product() {
    }

    public Product(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product<?> product = (Product<?>) o;
        return Objects.equals(id, product.id);
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
}
