package emt.proekt.bicycleshop.order.application.form;

import emt.proekt.bicycleshop.sharedkernel.domain.geo.City;
import emt.proekt.bicycleshop.sharedkernel.domain.geo.Country;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ShippingAddressForm implements Serializable {

    @NotEmpty
    private String address;
    @NotNull
    private City city;
    @NotNull
    private Country country;

}
