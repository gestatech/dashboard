package be.gestatech.core.api.persistence;

import static be.gestatech.core.api.persistence.CompositionType.AND;
import static be.gestatech.core.api.persistence.CompositionType.OR;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by amurifa on 7/07/2017.
 *
 * Helper class to easily combine {@link Specification} instances.
 */
public class Specifications<T> implements Specification<T>, Serializable {

	private static final long serialVersionUID = -3423879957774571829L;

	private final Specification<T> specification;

	private Specifications(Specification<T> specification) {
		this.specification = specification;
	}

	public static <T> Specifications<T> where(Specification<T> specification) {
		return new Specifications<T>(specification);
	}

	public Specifications<T> and(Specification<T> other) {
		return new Specifications<T>(new ComposedSpecification<T>(specification, other, AND));
	}

	public Specifications<T> or(Specification<T> other) {
		return new Specifications<T>(new ComposedSpecification<T>(specification, other, OR));
	}

	public static <T> Specifications<T> not(Specification<T> specification) {
		return new Specifications<T>(new NegatedSpecification<T>(specification));
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return Objects.isNull(specification)? null : specification.toPredicate(root, query, criteriaBuilder);
	}

}
