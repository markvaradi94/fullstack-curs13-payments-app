package ro.fasttrackit.payments.service.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.curs13homework.filters.PaymentFilters;
import ro.fasttrackit.payments.service.model.entity.PaymentEntity;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class PaymentDao {
    private final MongoTemplate mongo;

    public List<PaymentEntity> findAllPayments(PaymentFilters filters) {
        Query query = new Query();
        List<Criteria> criteria = buildCriteria(filters);

        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }

        return mongo.find(query, PaymentEntity.class);
    }

    private List<Criteria> buildCriteria(PaymentFilters filters) {
        List<Criteria> criteria = new ArrayList<>();

        ofNullable(filters.getId())
                .ifPresent(id -> criteria.add(Criteria.where("id").is(id)));
        ofNullable(filters.getStatus())
                .ifPresent(status -> criteria.add(Criteria.where("status").is(status)));
        ofNullable(filters.getAmountPayable())
                .ifPresent(amountPayable -> criteria.add(Criteria.where("amountPayable").is(amountPayable)));
        return criteria;
    }
}
