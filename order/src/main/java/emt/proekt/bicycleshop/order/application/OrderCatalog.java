package emt.proekt.bicycleshop.order.application;

import emt.proekt.bicycleshop.order.application.form.OrderForm;
import emt.proekt.bicycleshop.order.application.form.ShippingAddressForm;
import emt.proekt.bicycleshop.order.domain.event.OrderCanceled;
import emt.proekt.bicycleshop.order.domain.event.OrderCreated;
import emt.proekt.bicycleshop.order.domain.event.OrderItemAdded;
import emt.proekt.bicycleshop.order.domain.event.OrderItemDeleted;
import emt.proekt.bicycleshop.order.domain.model.Order;
import emt.proekt.bicycleshop.order.domain.model.OrderId;
import emt.proekt.bicycleshop.order.domain.model.ShippingAddress;
import emt.proekt.bicycleshop.order.domain.repository.OrderRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class OrderCatalog {

    private final OrderRepository orderRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final Validator validator;

    public OrderCatalog(OrderRepository orderRepository,
                        Validator validator,
                        ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.validator = validator;
    }

    @Transactional
    public OrderId createOrder(@NonNull OrderForm order) {
        Objects.requireNonNull(order,"order must not be null");
        var constraintViolations = validator.validate(order);

        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The OrderForm is not valid", constraintViolations);
        }

        var newOrder = orderRepository.saveAndFlush(toDomainModel(order));
        applicationEventPublisher.publishEvent(new OrderCreated(newOrder.id(),newOrder.getOrderedDate()));
        newOrder.getItems().forEach(orderItem -> applicationEventPublisher.publishEvent(new OrderItemAdded(newOrder.id(),orderItem.id(),orderItem.getProductId(),orderItem.getQuantity(), Instant.now())));
        return newOrder.id();
    }

    @Transactional
    public void cancelOrder(OrderId orderId){
        Objects.requireNonNull(orderId,"orderId must not be null");
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
        order.cancel();
        applicationEventPublisher.publishEvent(new OrderCanceled(order.id(),Instant.now()));
        order.getItems().forEach(orderItem -> applicationEventPublisher.publishEvent(new OrderItemDeleted(order.id(),orderItem.id(),orderItem.getProductId(),orderItem.getQuantity(),Instant.now())));

    }

    @NonNull
    public Optional<Order> findById(@NonNull OrderId orderId) {
        Objects.requireNonNull(orderId, "orderId must not be null");
        return orderRepository.findById(orderId);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @NonNull
    private Order toDomainModel(@NonNull OrderForm orderForm) {
        var order = new Order(Instant.now(), orderForm.getCurrency(),
                toDomainModel(orderForm.getShippingAddress()), orderForm.getUserId());
        orderForm.getItems().forEach(item -> order.addItem(item.getProduct(), item.getQuantity()));
        return order;
    }

    @NonNull
    private ShippingAddress toDomainModel(@NonNull ShippingAddressForm form) {
        return new ShippingAddress(form.getAddress(),form.getCity(), form.getCountry());
    }


}
