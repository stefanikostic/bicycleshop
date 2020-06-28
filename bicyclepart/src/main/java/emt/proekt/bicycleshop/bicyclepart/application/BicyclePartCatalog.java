package emt.proekt.bicycleshop.bicyclepart.application;

import emt.proekt.bicycleshop.bicyclepart.domain.model.BicyclePart;
import emt.proekt.bicycleshop.bicyclepart.domain.repository.BicyclePartRepository;
import emt.proekt.bicycleshop.bicyclepart.integration.OrderItemAddedEvent;
import emt.proekt.bicycleshop.bicyclepart.integration.OrderItemDeletedEvent;
import emt.proekt.bicycleshop.sharedkernel.domain.base.ProductId;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class BicyclePartCatalog {


    private final BicyclePartRepository bicyclePartRepository;

    public BicyclePartCatalog(BicyclePartRepository bicyclePartRepository) {
        this.bicyclePartRepository = bicyclePartRepository;
    }


    public List<BicyclePart> findAll(){
        return bicyclePartRepository.findAll();
    }

    public Optional<BicyclePart> findById(@NonNull ProductId productId){
        Objects.requireNonNull(productId, "productId mus not be null");
        return bicyclePartRepository.findById(productId);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderItemAdded(OrderItemAddedEvent event){
        BicyclePart bicyclePart = bicyclePartRepository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
        bicyclePart.subtractQuantity(event.getQuantity());
        bicyclePartRepository.save(bicyclePart);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderItemDeleted(OrderItemDeletedEvent event){
        BicyclePart bicyclePart = bicyclePartRepository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
        bicyclePart.addQuantity(event.getQuantity());
        bicyclePartRepository.save(bicyclePart);
    }

}
