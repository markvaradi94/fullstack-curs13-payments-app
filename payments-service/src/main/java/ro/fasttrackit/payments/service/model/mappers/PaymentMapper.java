package ro.fasttrackit.payments.service.model.mappers;

import dto.Payment;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs13homework.utils.ModelMapper;
import ro.fasttrackit.payments.service.model.entity.PaymentEntity;

@Component
public class PaymentMapper implements ModelMapper<Payment, PaymentEntity> {
    @Override
    public Payment toApi(PaymentEntity source) {
        return Payment.builder()
                .id(source.getId())
                .invoiceId(source.getInvoiceId())
                .status(source.getStatus())
                .amountPayable(source.getAmountPayable())
                .build();
    }

    @Override
    public PaymentEntity toEntity(Payment source) {
        return PaymentEntity.builder()
                .id(source.getId())
                .invoiceId(source.getInvoiceId())
                .status(source.getStatus())
                .amountPayable(source.getAmountPayable())
                .build();
    }
}
