package emt.proekt.bicycleshop.bicycle.application;

import emt.proekt.bicycleshop.bicycle.domain.model.Bicycle;
import emt.proekt.bicycleshop.bicycle.domain.repository.BicycleRepository;
import emt.proekt.bicycleshop.sharedkernel.domain.base.Product;
import emt.proekt.bicycleshop.sharedkernel.domain.base.ProductId;
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
public class BicycleCatalog {

    private final BicycleRepository bicycleRepository;

    public BicycleCatalog(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @NonNull
    public List<Bicycle> findAll() {
        return bicycleRepository.findAll();
    }

    @NonNull
    public Optional<Bicycle> findById(@NonNull ProductId productId) {
        Objects.requireNonNull(productId, "productId must not be null");
        return bicycleRepository.findById(productId);
    }
    

}
