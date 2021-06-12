package ro.fasttrackit.payments.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.payments.service.model.entity.PaymentEntryEntity;

public interface PaymentEntryRepository extends MongoRepository<PaymentEntryEntity, String> {
}
