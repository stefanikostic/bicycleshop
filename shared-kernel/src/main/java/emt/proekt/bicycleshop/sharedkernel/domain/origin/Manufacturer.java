package emt.proekt.bicycleshop.sharedkernel.domain.origin;

import emt.proekt.bicycleshop.sharedkernel.domain.base.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Manufacturer implements ValueObject {


    @Column(name="manufacturer_name")
    private String manufacturerName;

    @Column(name = "manufacturer_country")
    private String manufacturerCountry;
}
