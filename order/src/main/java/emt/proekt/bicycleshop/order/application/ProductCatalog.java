package emt.proekt.bicycleshop.order.application;

import emt.proekt.bicycleshop.order.domain.model.Product;
import emt.proekt.bicycleshop.order.domain.model.ProductId;

import java.util.List;

public interface ProductCatalog {

    List<Product> findAll();

    Product findById(ProductId id);
}
