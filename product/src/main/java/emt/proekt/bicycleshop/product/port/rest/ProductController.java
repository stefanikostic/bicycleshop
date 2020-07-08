package emt.proekt.bicycleshop.product.port.rest;

import emt.proekt.bicycleshop.product.application.ProductCatalog;
import emt.proekt.bicycleshop.product.domain.model.Product;
import emt.proekt.bicycleshop.product.domain.model.ProductId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductCatalog productCatalog;

    public ProductController(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String productId) {
        return productCatalog.findById(new ProductId(productId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Product> findAll() {
        return productCatalog.findAll();
    }
}
