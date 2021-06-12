package ro.fasttrackit.payments.service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs13homework.exceptions.ResourceNotFoundException;
import ro.fasttrackit.curs13homework.filters.PaymentFilters;
import ro.fasttrackit.payments.service.model.entity.PaymentEntity;
import ro.fasttrackit.payments.service.repository.PaymentDao;
import ro.fasttrackit.payments.service.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.unmodifiableList;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentDao dao;
    private final PaymentRepository repository;
    private final ObjectMapper mapper;

    public List<PaymentEntity> findAllPayments(PaymentFilters filters) {
        return unmodifiableList(dao.findAllPayments(filters));
    }

    public Optional<PaymentEntity> findPayment(String paymentId) {
        return repository.findById(paymentId);
    }

    public PaymentEntity addPayment(PaymentEntity newPayment) {
        newPayment.setId(null);
        return repository.save(newPayment);
    }

    public Optional<PaymentEntity> deletePayment(String paymentId) {
        Optional<PaymentEntity> paymentToDelete = repository.findById(paymentId);
        paymentToDelete.ifPresent(this::deleteExistingPayment);
        return paymentToDelete;
    }

    @SneakyThrows
    public PaymentEntity patchPayment(String paymentId, JsonPatch patch) {
        PaymentEntity dbPayment = getOrThrow(paymentId);
        JsonNode patchedPaymentJson = patch.apply(mapper.valueToTree(dbPayment));
        PaymentEntity patchedPayment = mapper.treeToValue(patchedPaymentJson, PaymentEntity.class);
        copyPayment(patchedPayment, dbPayment);
        return repository.save(dbPayment);
    }

    private void deleteExistingPayment(PaymentEntity paymentEntity) {
        log.info("Deleting payment: " + paymentEntity);
        repository.delete(paymentEntity);
    }

    private void copyPayment(PaymentEntity newPayment, PaymentEntity dbPayment) {
        dbPayment.setStatus(newPayment.getStatus());
    }

    private PaymentEntity getOrThrow(String paymentId) {
        return repository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find payment with id: " + paymentId));
    }
}
