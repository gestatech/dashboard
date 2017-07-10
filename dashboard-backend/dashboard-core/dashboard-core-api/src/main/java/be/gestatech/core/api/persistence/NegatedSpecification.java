package be.gestatech.core.api.persistence;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by amurifa on 7/07/2017.
 */
public class NegatedSpecification<T> implements Specification<T>, Serializable {

	private static final long serialVersionUID = -8231912408868857387L;

	private final Specification<T> specification;

	public NegatedSpecification(Specification<T> specification) {
		this.specification = specification;
	}

	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return Objects.isNull(specification) ? null : builder.not(specification.toPredicate(root, query, builder));
	}
}