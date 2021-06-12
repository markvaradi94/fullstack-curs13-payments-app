package ro.fasttrackit.payments.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs13homework.payments.events.PaymentEvent;
import ro.fasttrackit.payments.service.model.entity.PaymentEntity;
import ro.fasttrackit.payments.service.model.mappers.PaymentMapper;

import static ro.fasttrackit.curs13homework.payments.events.PaymentEventType.COMPLETED;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentNotificationsService {
    private final PaymentMapper mapper;
    private final RabbitTemplate rabbitTemplate;
    private final FanoutExchange paymentExchange;

    public void notifyPaymentCompleted(PaymentEntity payment) {
        PaymentEvent event = PaymentEvent.builder()
                .payment(mapper.toApi(payment))
                .type(COMPLETED)
                .build();
        log.info("Sending event: " + event);
        rabbitTemplate.convertAndSend(paymentExchange.getName(), "", event);
    }
}
