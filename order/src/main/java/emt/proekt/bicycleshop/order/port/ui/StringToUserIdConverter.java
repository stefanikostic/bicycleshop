package emt.proekt.bicycleshop.order.port.ui;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import emt.proekt.bicycleshop.order.domain.model.UserId;

public class StringToUserIdConverter implements Converter<String, UserId> {

    @Override
    public Result<UserId> convertToModel(String value, ValueContext context) {
        return value == null ? Result.ok(null) : Result.ok(new UserId(value));
    }

    @Override
    public String convertToPresentation(UserId value, ValueContext context) {
        return value == null ? "" : value.toString();
    }
}