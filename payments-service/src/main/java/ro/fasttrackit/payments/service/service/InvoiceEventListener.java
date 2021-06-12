package ro.fasttrackit.payments.service.service;

import events.InvoiceEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.fasttrackit.payments.service.model.entity.PaymentEntryEntity;

import static events.InvoiceEventType.CREATED;

@Slf4j
@Component
@RequiredArgsConstructor
public class InvoiceEventListener {
    private final PaymentEntryService paymentEntryService;

    @RabbitListener(queues = "#{invoiceQue.name}")
    void processInvoiceEvent(InvoiceEvent event) {
        if (event.getType() == CREATED) {
            PaymentEntryEntity paymentEntry = paymentEntryService.createNewPaymentEntryForInvoice(event.getInvoice().getId());
            log.info("Created payment entry for invoice: " + event.getInvoice() + ", payment: " + paymentEntry);
        }
    }

}
