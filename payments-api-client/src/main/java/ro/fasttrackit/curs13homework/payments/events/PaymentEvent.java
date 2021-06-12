package ro.fasttrackit.curs13homework.payments.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import dto.Payment;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = PaymentEvent.PaymentEventBuilder.class)
public class PaymentEvent {
    Payment payment;
    PaymentEventType type;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PaymentEventBuilder {

    }
}
