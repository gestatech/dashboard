package be.gestatech.core.api.persistence;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by amurifa on 7/07/2017.
 *
 * Specification in the sense of Domain Driven Design.
 */
public interface Specification<T> {

	Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
}
