package emt.proekt.bicycleshop.bicycle.application;


import emt.proekt.bicycleshop.bicycle.domain.model.Bicycle;
import emt.proekt.bicycleshop.bicycle.domain.repository.BicycleRepository;
import emt.proekt.bicycleshop.bicycle.integration.OrderItemAddedEvent;
import emt.proekt.bicycleshop.bicycle.integration.OrderItemDeletedEvent;
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


    public List<Bicycle> findAll(){
        return bicycleRepository.findAll();
    }

   public Optional<Bicycle> findById(@NonNull ProductId productId){
       Objects.requireNonNull(productId, "productId mus not be null");
       return bicycleRepository.findById(productId);
   }

   @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
   public void onOrderItemAdded(OrderItemAddedEvent event){
        Bicycle bicycle = bicycleRepository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
        bicycle.subtractQuantity(event.getQuantity());
        bicycleRepository.save(bicycle);
   }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderItemDeleted(OrderItemDeletedEvent event){
        Bicycle bicycle = bicycleRepository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
        bicycle.addQuantity(event.getQuantity());
        bicycleRepository.save(bicycle);
    }

}
