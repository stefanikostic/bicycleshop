package emt.proekt.bicycleshop.sharedkernel.port.ui;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import emt.proekt.bicycleshop.sharedkernel.domain.geo.City;

public class StringToCityConverter implements Converter<String, City> {

    @Override
    public Result<City> convertToModel(String value, ValueContext context) {
        return value == null ? Result.ok(null) : Result.ok(new City(value));
    }

    @Override
    public String convertToPresentation(City value, ValueContext context) {
        return value == null ? "" : value.toString();
    }
}
