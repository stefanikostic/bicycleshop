package emt.proekt.bicycleshop.user.domain.model;

import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class UserId extends DomainObjectId {

    private UserId() {
        super(DomainObjectId.randomId(UserId.class).toString());
    }

    public UserId(String id) {
        super(id);
    }
}
