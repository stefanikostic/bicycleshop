package emt.proekt.bicycleshop.order.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
public class OrderItemId extends DomainObjectId {

    private OrderItemId() {
        super("");
    }

    public OrderItemId(String id) {
        super(id);
    }
}
