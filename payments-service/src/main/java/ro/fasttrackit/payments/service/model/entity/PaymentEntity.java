package ro.fasttrackit.payments.service.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.fasttrackit.curs13homework.enums.PaymentStatus;

@Data
@Builder
@Document(collection = "payments")

public class PaymentEntity {
    @Id
    private String id;

    private PaymentStatus status;
    private Double amountPayable;
}
