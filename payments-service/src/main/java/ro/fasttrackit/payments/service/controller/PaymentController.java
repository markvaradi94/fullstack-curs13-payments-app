package ro.fasttrackit.payments.service.controller;

import dto.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs13homework.exceptions.ResourceNotFoundException;
import ro.fasttrackit.curs13homework.filters.PaymentFilters;
import ro.fasttrackit.payments.service.model.mappers.PaymentMapper;
import ro.fasttrackit.payments.service.service.PaymentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;
    private final PaymentMapper mapper;

    @GetMapping
    List<Payment> getAllPayments(PaymentFilters filters) {
        return mapper.toApi(service.findAllPayments(filters));
    }

    @GetMapping("{paymentId}")
    Payment getPayment(@PathVariable String paymentId) {
        return service.findPayment(paymentId)
                .map(mapper::toApi)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find payment with id: " + paymentId));
    }

    @PostMapping
    Payment addPayment(@Valid @RequestBody Payment payment) {
        return mapper.toApi(service.addPayment(mapper.toEntity(payment)));
    }

    @DeleteMapping("{paymentId}")
    Payment deletePayment(@PathVariable String paymentId) {
        return service.deletePayment(paymentId)
                .map(mapper::toApi)
                .orElse(null);
    }
}
