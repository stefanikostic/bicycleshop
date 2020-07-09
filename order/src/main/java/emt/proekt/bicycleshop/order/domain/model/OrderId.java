package emt.proekt.bicycleshop.order.domain.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
public class OrderId extends DomainObjectId {

    private OrderId() {
        super("");
    }

    @JsonCreator
    public OrderId(String id) {
        super(id);
    }
}
