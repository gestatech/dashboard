package be.gestatech.core.api.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

/**
 * Created by amurifa on 7/07/2017.
 */
public enum CompositionType {

	AND {
		@Override
		public Predicate combine(CriteriaBuilder builder, Predicate lefHandSide, Predicate rightHandSide) {
			return builder.and(lefHandSide, rightHandSide);
		}
	},

	OR {
		@Override
		public Predicate combine(CriteriaBuilder builder, Predicate lefHandSide, Predicate rightHandSide) {
			return builder.or(lefHandSide, rightHandSide);
		}
	};

	abstract Predicate combine(CriteriaBuilder builder, Predicate lefHandSide, Predicate rightHandSide);
}
