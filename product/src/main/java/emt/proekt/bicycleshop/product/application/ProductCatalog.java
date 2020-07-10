package emt.proekt.bicycleshop.product.application;

import emt.proekt.bicycleshop.product.domain.model.Product;
import emt.proekt.bicycleshop.product.domain.repository.ProductRepository;
import emt.proekt.bicycleshop.product.domain.model.ProductId;
import emt.proekt.bicycleshop.product.integration.OrderItemAddedEvent;
import emt.proekt.bicycleshop.product.integration.OrderItemDeletedEvent;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ProductCatalog {

    private final ProductRepository productRepository;

    public ProductCatalog(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(@NonNull ProductId productId) {
        Objects.requireNonNull(productId, "productId mus not be null");
        return productRepository.findById(productId);
    }

/*    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderItemAdded(OrderItemAddedEvent event){
        Product product = productRepository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
        product.subtractQuantity(event.getQuantity());
        productRepository.save(product);
    }*/

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderItemDeleted(OrderItemDeletedEvent event){
        Product product = productRepository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
        product.addQuantity(event.getQuantity());
        productRepository.save(product);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderCreated(OrderItemAddedEvent event) {
        Product p = productRepository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
        p.subtractQuantity(event.getQuantity());
        productRepository.save(p);
    }
}
