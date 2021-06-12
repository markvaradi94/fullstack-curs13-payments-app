package ro.fasttrackit.payments.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentNotificationsService {
    private final RabbitTemplate rabbitTemplate;
    private final FanoutExchange invoiceExchange;


}
