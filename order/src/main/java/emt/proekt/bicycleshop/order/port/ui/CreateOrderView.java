package emt.proekt.bicycleshop.order.port.ui;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import emt.proekt.bicycleshop.order.application.OrderCatalog;
import emt.proekt.bicycleshop.order.application.ProductCatalog;
import emt.proekt.bicycleshop.order.application.form.OrderForm;
import emt.proekt.bicycleshop.order.application.form.OrderItemForm;
import emt.proekt.bicycleshop.order.application.form.ShippingAddressForm;
import emt.proekt.bicycleshop.order.domain.model.Product;
import emt.proekt.bicycleshop.sharedkernel.domain.financial.Currency;
import emt.proekt.bicycleshop.sharedkernel.domain.geo.Country;
import emt.proekt.bicycleshop.sharedkernel.port.ui.StringToCityConverter;


@Route("create-order")
@PageTitle("Create Order")
public class CreateOrderView extends VerticalLayout {

    private final ProductCatalog productCatalog;
    private final OrderCatalog orderCatalog;
    private final Binder<OrderForm> binder;
    private final Grid<OrderItemForm> itemGrid;

    public CreateOrderView(ProductCatalog productCatalog, OrderCatalog orderCatalog) {
        this.productCatalog = productCatalog;
        this.orderCatalog = orderCatalog;

        setSizeFull();

        binder = new Binder<>();

        var title = new Html("<h3>Create Order</h3>");
        add(title);

        itemGrid = new Grid<>();
        itemGrid.addColumn(form -> form.getProduct().getProductModel()).setHeader("Product");
        itemGrid.addColumn(OrderItemForm::getQuantity).setHeader("Qty");

        var orderItemLayout = new OrderItemLayout();

        add(itemGrid);
        add(orderItemLayout);

        var shippingAddress = new AddressLayout();
        shippingAddress.bind(binder, OrderForm::getShippingAddress);
        add(shippingAddress);
        var currency = new ComboBox<>("Currency", Currency.values());
        binder.forField(currency)
                .asRequired()
                .bind(OrderForm::getCurrency, OrderForm::setCurrency);
        add(currency);

        TextField user = new TextField("User id");
        binder.forField(user)
                .asRequired()
                .withConverter(new StringToUserIdConverter())
                .bind(OrderForm::getUserId, OrderForm::setUserId);

        add(user);

        var createOrder = new Button("Create Order", evt -> createOrder());
        createOrder.setEnabled(false);
        createOrder.getElement().getThemeList().set("primary", true);


        add(createOrder);

        binder.setBean(new OrderForm());
        binder.addValueChangeListener(evt -> createOrder.setEnabled(binder.isValid()));
    }


    private void addItem(OrderItemForm item) {
        binder.getBean().getItems().add(item);
        itemGrid.setItems(binder.getBean().getItems());
    }

    private void createOrder() {
        try {
            var orderId = orderCatalog.createOrder(binder.getBean());
            getUI().ifPresent(ui -> ui.navigate(OrderDetailsView.class, orderId.getId()));
        } catch (Exception ex) {
            Notification.show(ex.getMessage());
        }
    }

    class AddressLayout extends VerticalLayout {
        private static final long serialVersionUID = -4258304683924429984L;

        private TextField address;
        private TextField city;
        private TextField user;
        private ComboBox<Country> country;

        AddressLayout() {
            setPadding(false);
            setWidth("630px");

            address = createTextField("Address line 1");
            city = createTextField("City");
            country = new ComboBox<>("Country", Country.values());
            country.setWidth("100%");


            var line1 = new HorizontalLayout(city, address);
            line1.setWidth("100%");

            var line2 = new HorizontalLayout(country);
            line2.setWidth("100%");

            add(line1, line2);
        }

        private TextField createTextField(String caption) {
            var field = new TextField(caption);
            field.setWidth("70%");
            return field;
        }

        private void bind(Binder<OrderForm> binder, ValueProvider<OrderForm, ShippingAddressForm> parentProvider) {
            binder.forField(address)
                    .asRequired()
                    .bind(getter(parentProvider, ShippingAddressForm::getAddress), setter(parentProvider, ShippingAddressForm::setAddress));

            binder.forField(city)
                    .asRequired()
                    .withConverter(new StringToCityConverter())
                    .bind(getter(parentProvider, ShippingAddressForm::getCity), setter(parentProvider, ShippingAddressForm::setCity));
            binder.forField(country)
                    .asRequired()
                    .bind(getter(parentProvider, ShippingAddressForm::getCountry), setter(parentProvider, ShippingAddressForm::setCountry));
        }

        private <V> ValueProvider<OrderForm, V> getter(ValueProvider<OrderForm, ShippingAddressForm> parentProvider, ValueProvider<ShippingAddressForm, V> valueProvider) {
            return orderForm -> valueProvider.apply(parentProvider.apply(orderForm));
        }

        private <V> Setter<OrderForm, V> setter(ValueProvider<OrderForm, ShippingAddressForm> parentProvider, Setter<ShippingAddressForm, V> setter) {
            return (orderForm, value) -> setter.accept(parentProvider.apply(orderForm), value);
        }
    }

    class OrderItemLayout extends HorizontalLayout {

        private Binder<OrderItemForm> binder;
        private ComboBox<Product> product;
        private TextField quantity;
        private TextField itemPrice;
        //        private TextField tax;
        private Button addItem;

        OrderItemLayout() {
            setWidth("630px");

            setAlignItems(Alignment.END);
            product = new ComboBox<>("Product", productCatalog.findAll());
            product.setItemLabelGenerator(Product::getProductModel);
            add(product);

//            tax = new TextField("VAT");
//            tax.setReadOnly(true);
//            tax.setWidth("60px");
//            add(tax);

            itemPrice = new TextField("Price");
            itemPrice.setReadOnly(true);
            itemPrice.setWidth("100%");
            add(itemPrice);

            quantity = new TextField("Qty");
            quantity.setWidth("50px");
            add(quantity);

            addItem = new Button("Add", evt -> {
                addItem(binder.getBean());
                binder.setBean(new OrderItemForm());
                addItem.setEnabled(false);
            });
            addItem.setEnabled(false);
            add(addItem);

            product.addValueChangeListener(evt -> {
                var p = evt.getValue();
                if (p == null) {
                    itemPrice.setValue("");
                } else {
                    itemPrice.setValue(p.getProductPrice().toString());
                }
            });

            binder = new Binder<>();
            binder.forField(product)
                    .asRequired()
                    .bind(OrderItemForm::getProduct, OrderItemForm::setProduct);
            binder.forField(quantity)
                    .asRequired()
                    .withConverter(new StringToIntegerConverter("Please enter a valid quantity"))
                    .bind(OrderItemForm::getQuantity, OrderItemForm::setQuantity);
            binder.addValueChangeListener(evt -> addItem.setEnabled(binder.isValid()));
            binder.setBean(new OrderItemForm());
        }
    }
}
