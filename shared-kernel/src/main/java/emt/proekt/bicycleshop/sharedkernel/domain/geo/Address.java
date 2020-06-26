package emt.proekt.bicycleshop.sharedkernel.domain.geo;

import emt.proekt.bicycleshop.sharedkernel.domain.base.ValueObject;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Embeddable
@Getter
@MappedSuperclass
public class Address implements ValueObject {

    @Column(name = "address")
    private String address;

    @Column(name = "city_name")
    @Embedded
    private City city;

    @Column(name = "country")
    private Country country;

    @SuppressWarnings("unused") // Used by JPA only.
    protected Address() {
    }

    public Address(@NonNull String address, @NonNull City city,
                   @NonNull Country country) {
        this.address = address;
        this.city = city;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(address, address.address) &&
                Objects.equals(city, address.city) &&
                country == address.country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address,  city,  country);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(address);
        sb.append(", ");
        sb.append(city);
        sb.append(", ");
        sb.append(country);
        return sb.toString();
    }



}
