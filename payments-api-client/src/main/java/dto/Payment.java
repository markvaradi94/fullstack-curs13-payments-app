package dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import ro.fasttrackit.curs13homework.enums.PaymentStatus;

@Value
@Builder
@JsonDeserialize(builder = Payment.PaymentBuilder.class)
public class Payment {
    String id;
    PaymentStatus status;
    Double amountPayable;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PaymentBuilder {
    }
}
