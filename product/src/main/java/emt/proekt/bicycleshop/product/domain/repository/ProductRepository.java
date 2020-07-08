package emt.proekt.bicycleshop.product.domain.repository;

import emt.proekt.bicycleshop.product.domain.model.Product;
import emt.proekt.bicycleshop.product.domain.model.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductId> {
}
