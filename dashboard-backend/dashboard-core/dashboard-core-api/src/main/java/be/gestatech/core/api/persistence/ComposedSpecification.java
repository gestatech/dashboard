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
public class ComposedSpecification<T> implements Specification<T>, Serializable {

	private static final long serialVersionUID = 1L;

	private final Specification<T> lefHandSide;

	private final Specification<T> rightHandSide;

	private final CompositionType compositionType;

	protected ComposedSpecification(Specification<T> lefHandSide, Specification<T> rightHandSide, CompositionType compositionType) {
		Objects.requireNonNull(compositionType, "CompositionType must not be null!");

		this.lefHandSide = lefHandSide;
		this.rightHandSide = rightHandSide;
		this.compositionType = compositionType;
	}

	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Predicate otherPredicate = Objects.isNull(rightHandSide) ? null : rightHandSide.toPredicate(root, query, criteriaBuilder);
		Predicate thisPredicate = Objects.isNull(lefHandSide) ? null : lefHandSide.toPredicate(root, query, criteriaBuilder);
		return Objects.isNull(thisPredicate) ? otherPredicate : Objects.isNull(otherPredicate) ? thisPredicate : this.compositionType.combine(criteriaBuilder, thisPredicate, otherPredicate);
	}
}