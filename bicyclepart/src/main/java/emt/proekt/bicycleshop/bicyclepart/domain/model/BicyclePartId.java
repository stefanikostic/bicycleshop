package emt.proekt.bicycleshop.bicyclepart.domain.model;


import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class BicyclePartId extends DomainObjectId {
    public BicyclePartId(String id) {
        super(id);
    }
}
