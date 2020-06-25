package emt.proekt.bicycleshop.order.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;

public class OrderItemId extends DomainObjectId {

    private String id;

    public OrderItemId(String id) {
        super(id);
    }
}
