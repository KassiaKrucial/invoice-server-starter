package cz.itnetwork.entity.repository.specification;

import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.InvoiceEntity_;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.PersonEntity_;
import cz.itnetwork.entity.repository.filter.InvoiceFilter;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains queries for {@link InvoiceFilter} to filter invoices via java and not sql
 */
@RequiredArgsConstructor
public class InvoiceSpecification implements Specification<InvoiceEntity> {

    /**
     * Represents an instance of {@link InvoiceFilter}
     */
    private final InvoiceFilter filter;

    /**
     * Filters invoices by: product, minPrice, maxPrice, buyerId, sellerId
     * @param root the class {@link InvoiceEntity} containing properties to filter
     * @param query defines query structure
     * @param criteriaBuilder creates typed queries
     * @return query for filtering invoices
     */
    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getProduct() != null) {
            predicates.add(criteriaBuilder.like(root.get(InvoiceEntity_.PRODUCT), ("%" + filter.getProduct()) + "%"));
        }

        if (filter.getMinPrice() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(InvoiceEntity_.PRICE), filter.getMinPrice()));
        }

        if (filter.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(InvoiceEntity_.PRICE), filter.getMaxPrice()));
        }

        if (filter.getBuyerID() != null) {
            Join<PersonEntity, InvoiceEntity> buyerJoin =
                    root.join(InvoiceEntity_.BUYER);
            predicates.add(criteriaBuilder.equal(buyerJoin.get(PersonEntity_.ID), filter.getBuyerID()));
        }

        if (filter.getSellerID() != null) {
            Join<PersonEntity, InvoiceEntity> sellerJoin =
                    root.join(InvoiceEntity_.SELLER);
            predicates.add(criteriaBuilder.equal(sellerJoin.get(PersonEntity_.ID), filter.getSellerID()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}