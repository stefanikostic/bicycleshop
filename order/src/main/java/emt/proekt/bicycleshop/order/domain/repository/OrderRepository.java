package emt.proekt.bicycleshop.order.domain.repository;

import emt.proekt.bicycleshop.order.domain.model.Order;
import emt.proekt.bicycleshop.order.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderId> {
}
