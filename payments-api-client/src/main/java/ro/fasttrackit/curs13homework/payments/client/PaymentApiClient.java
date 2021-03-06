package ro.fasttrackit.curs13homework.payments.client;

import dto.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.fasttrackit.curs13homework.filters.PaymentFilters;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Component
public class PaymentApiClient {
    private final String hostname;
    private final RestTemplate restTemplate;

    public PaymentApiClient(@Value("${payments-service-host:NOT_DEFINED}") String hostname) {
        this.hostname = hostname;
        this.restTemplate = new RestTemplate();
    }

    public List<Payment> getAllPayments(PaymentFilters filters) {
        String url = buildQueriedUrl(filters);
        return restTemplate.exchange(
                url,
                GET,
                EMPTY,
                new ParameterizedTypeReference<List<Payment>>() {
                }
        ).getBody();
    }

    public Optional<Payment> getPayment(String paymentId) {
        String url = UriComponentsBuilder.fromHttpUrl(hostname)
                .path("/payments/")
                .path(paymentId)
                .toUriString();
        try {
            return ofNullable(restTemplate.getForObject(url, Payment.class));
        } catch (HttpClientErrorException exception) {
            return empty();
        }
    }

    public Payment addPayment(Payment payment) {
        String url = UriComponentsBuilder.fromHttpUrl(hostname)
                .path("/payments")
                .toUriString();
        return restTemplate.postForObject(url, payment, Payment.class);
    }

    public Payment deletePayment(String paymentId) {
        String url = UriComponentsBuilder.fromHttpUrl(hostname)
                .path("/payments/")
                .path(paymentId)
                .toUriString();
        return restTemplate.exchange(
                url,
                DELETE,
                EMPTY,
                Payment.class
        ).getBody();
    }

    private String buildQueriedUrl(PaymentFilters filters) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(hostname)
                .path("/payments/");
        ofNullable(filters.getId())
                .ifPresent(id -> builder.queryParam("id", id));
        ofNullable(filters.getInvoiceId())
                .ifPresent(invoiceId -> builder.queryParam("invoiceId", invoiceId));
        ofNullable(filters.getStatus())
                .ifPresent(status -> builder.queryParam("status", status));
        ofNullable(filters.getAmountPayable())
                .ifPresent(amountPayable -> builder.queryParam("amountPayable", amountPayable));
        return builder.toUriString();
    }
}
