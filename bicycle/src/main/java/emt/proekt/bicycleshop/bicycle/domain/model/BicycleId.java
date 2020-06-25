package emt.proekt.bicycleshop.bicycle.domain.model;


import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class BicycleId extends DomainObjectId {

    public BicycleId(String id) {
        super(id);
    }
}
