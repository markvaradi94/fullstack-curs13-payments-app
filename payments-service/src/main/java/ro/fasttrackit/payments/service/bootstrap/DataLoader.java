package ro.fasttrackit.payments.service.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.fasttrackit.payments.service.model.entity.PaymentEntity;
import ro.fasttrackit.payments.service.service.PaymentService;

import static ro.fasttrackit.curs13homework.enums.PaymentStatus.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final PaymentService service;

    @Override
    public void run(String... args) throws Exception {
//        service.addPayment(
//                PaymentEntity.builder()
//                        .status(PENDING)
//                        .amountPayable(500.0)
//                        .build()
//        );
//        service.addPayment(
//                PaymentEntity.builder()
//                        .status(DONE)
//                        .amountPayable(72.0)
//                        .build()
//        );
//        service.addPayment(
//                PaymentEntity.builder()
//                        .status(PENDING)
//                        .amountPayable(19.0)
//                        .build()
//        );
//        service.addPayment(
//                PaymentEntity.builder()
//                        .status(REJECTED)
//                        .amountPayable(25500.0)
//                        .build()
//        );
    }
}
