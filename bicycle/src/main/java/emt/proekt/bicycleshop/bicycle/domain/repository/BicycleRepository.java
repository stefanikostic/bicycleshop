package emt.proekt.bicycleshop.bicycle.domain.repository;

import emt.proekt.bicycleshop.bicycle.domain.model.Bicycle;
import emt.proekt.bicycleshop.sharedkernel.domain.base.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BicycleRepository extends JpaRepository<Bicycle, ProductId> {
}
