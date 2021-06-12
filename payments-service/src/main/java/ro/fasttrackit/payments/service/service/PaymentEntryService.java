package ro.fasttrackit.payments.service.service;

import dto.Invoice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs13homework.enums.PaymentStatus;
import ro.fasttrackit.curs13homework.exceptions.ResourceNotFoundException;
import ro.fasttrackit.curs13homework.invoice.client.InvoiceApiClient;
import ro.fasttrackit.payments.service.model.entity.PaymentEntryEntity;
import ro.fasttrackit.payments.service.repository.PaymentEntryRepository;

import static ro.fasttrackit.curs13homework.enums.PaymentStatus.PENDING;
import static ro.fasttrackit.curs13homework.enums.PaymentStatus.REJECTED;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentEntryService {
    private final InvoiceApiClient invoiceClient;
    private final PaymentEntryRepository repository;

    public PaymentEntryEntity createNewPaymentEntryForInvoice(String invoiceId) {
        Invoice invoice = invoiceClient.getInvoice(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find invoice with id " + invoiceId));
        return repository.save(
                PaymentEntryEntity.builder()
                        .amountPayable(invoice.getAmount())
                        .sender(invoice.getSender())
                        .receiver(invoice.getReceiver())
                        .status(statusForSenderAndReceiver(invoice))
                        .build()
        );
    }

    private PaymentStatus statusForSenderAndReceiver(Invoice invoice) {
        if (invoice.getSender() == null || invoice.getReceiver() == null) {
            return REJECTED;
        } else {
            return PENDING;
        }
    }
}
