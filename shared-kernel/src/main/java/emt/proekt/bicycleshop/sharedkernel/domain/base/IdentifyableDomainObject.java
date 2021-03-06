package emt.proekt.bicycleshop.sharedkernel.domain.base;

import org.springframework.lang.Nullable;

import java.io.Serializable;

public interface IdentifyableDomainObject<ID extends Serializable> extends DomainObject {

    @Nullable
    ID id();
}
