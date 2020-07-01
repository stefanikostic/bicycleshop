package emt.proekt.bicycleshop.bicyclepart.domain.repository;

import emt.proekt.bicycleshop.bicyclepart.domain.model.BicyclePart;
import emt.proekt.bicycleshop.sharedkernel.domain.base.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BicyclePartRepository extends JpaRepository<BicyclePart, ProductId> {
}
