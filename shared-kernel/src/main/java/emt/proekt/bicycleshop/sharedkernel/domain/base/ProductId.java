package emt.proekt.bicycleshop.sharedkernel.domain.base;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Embeddable;

@Embeddable
public class ProductId extends DomainObjectId {

    private ProductId() {
        super(DomainObjectId.randomId(ProductId.class).toString());
    }

    @JsonCreator
    public ProductId(String id) {
        super(id);
    }

}
