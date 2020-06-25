package emt.proekt.bicycleshop.order.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;

public class ProductId extends DomainObjectId {
    public ProductId() {
        super(DomainObjectId.randomId(ProductId.class).toString());
    }

    @JsonCreator
    public ProductId(String id) {
        super(id);
    }
}
