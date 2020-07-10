package emt.proekt.bicycleshop.product.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderId extends DomainObjectId {

    private OrderId() {
        super(DomainObjectId.randomId(OrderId.class).getId());
    }

    public OrderId(String id) {
        super(id);
    }
}
