package emt.proekt.bicycleshop.sharedkernel.domain.base;

import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@MappedSuperclass
public abstract class AbstractEntity<ID extends DomainObjectId> implements IdentifyableDomainObject<ID> {

    @EmbeddedId
    protected ID id;

    public AbstractEntity() {

    }

    public AbstractEntity(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getClass().getSimpleName(), id);
    }
}
