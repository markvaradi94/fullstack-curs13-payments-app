package ro.fasttrackit.payments.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.payments.service.model.entity.PaymentEntity;

public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {
}
