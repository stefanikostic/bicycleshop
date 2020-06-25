package emt.proekt.bicycleshop.order.domain.model;


import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderId extends DomainObjectId {
    public OrderId(String id) {
        super(id);
    }
}
