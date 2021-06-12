package ro.fasttrackit.payments.service.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    FanoutExchange paymentExchange() {
        return new FanoutExchange("payment.fanout");
    }

    @Bean
    Queue paymentQue() {
        return new AnonymousQueue();
    }

    @Bean
    Binding paymentBinding(Queue paymentQue, FanoutExchange paymentExchange) {
        return BindingBuilder.bind(paymentQue).to(paymentExchange);
    }
}
