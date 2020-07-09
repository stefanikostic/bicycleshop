package emt.proekt.bicycleshop.order.port.ui;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import emt.proekt.bicycleshop.order.application.OrderCatalog;
import emt.proekt.bicycleshop.order.domain.model.Order;
import emt.proekt.bicycleshop.order.domain.model.OrderId;
import emt.proekt.bicycleshop.order.domain.model.OrderItem;
import emt.proekt.bicycleshop.order.domain.model.ShippingAddress;

import java.util.Optional;

@Route("order")
@PageTitle("Show Order")
public class OrderDetailsView extends VerticalLayout implements HasUrlParameter<String> {

    private final OrderCatalog orderCatalog;

    public OrderDetailsView(OrderCatalog orderCatalog) {
        this.orderCatalog = orderCatalog;
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        Optional<Order> order = Optional.ofNullable(parameter).map(OrderId::new).flatMap(orderCatalog::findById);
        if (order.isPresent()) {
            showOrder(order.get());
        } else {
            showNoSuchOrder();
        }
    }

    private void showOrder(Order order) {
        var title = new Html("<h3>Order Details</h3>");
        add(title);

        var header = new FormLayout();
        header.addFormItem(createReadOnlyTextField(order.getOrderedDate().toString()), "Ordered date");
        header.addFormItem(createReadOnlyTextField(order.state().name()), "State");
        header.addFormItem(createReadOnlyAddressArea(order.getShippingAddress()), "Billing Address");
        add(header);

        var items = new Grid<OrderItem>();
        items.addColumn(OrderItem::getQuantity).setHeader("Qty");
        items.addColumn(OrderItem::getItemPrice).setHeader("Price");
        var subtotalExcludingTax = items.addColumn(OrderItem::subtotal).setHeader("Subtotal");
        items.setItems(order.getItems());
        var footerRow = items.appendFooterRow();
        footerRow.getCell(subtotalExcludingTax).setText(order.total().toString());
        add(items);
    }

    private TextField createReadOnlyTextField(String value) {
        var textField = new TextField();
        textField.setReadOnly(true);
        textField.setValue(value);
        return textField;
    }

    private TextArea createReadOnlyAddressArea(ShippingAddress address) {
        var textArea = new TextArea();
        textArea.setHeight("140px");
        textArea.setValue(formatAddress(address));
        textArea.setReadOnly(true);
        return textArea;
    }

    private String formatAddress(ShippingAddress address) {
        var sb = new StringBuilder();
        sb.append(address.address()).append("\n");
        sb.append(Optional.ofNullable(address.address()).orElse("")).append("\n");
        sb.append(address.city()).append("\n");
        sb.append(address.country());
        return sb.toString();
    }

    private void showNoSuchOrder() {
        add(new Text("The order does not exist."));
    }
}
