package emt.proekt.bicycleshop.bicycle.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemId extends DomainObjectId {

    private String id;

    private OrderItemId() {
        super(DomainObjectId.randomId(OrderItemId.class).toString());
    }

    @JsonCreator
    public OrderItemId(String id) {
        super(id);
        this.id=id;
    }
}
