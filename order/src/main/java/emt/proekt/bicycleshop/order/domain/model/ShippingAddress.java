package emt.proekt.bicycleshop.order.domain.model;


import emt.proekt.bicycleshop.sharedkernel.domain.geo.Address;
import emt.proekt.bicycleshop.sharedkernel.domain.geo.City;
import emt.proekt.bicycleshop.sharedkernel.domain.geo.Country;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;

@Embeddable
public class ShippingAddress extends Address {

    protected ShippingAddress() {
    }

    public ShippingAddress(@NonNull String address,
                           @NonNull City city, @NonNull Country country) {
        super(address, city, country);
    }


}
