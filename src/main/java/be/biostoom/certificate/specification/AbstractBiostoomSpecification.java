package eu.europa.ec.jrc.milc.specification;

import eu.europa.ec.jrc.milc.domain.MILCUser;
import eu.europa.ec.jrc.milc.utility.CurrentUserProvider;
import eu.europa.ec.jrc.milc.utility.QueryPredicateBuilder;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;

public abstract class AbstractMILCSpecification<T> implements MILCSpecification<T> {

	private static final long serialVersionUID = 3736157462129598220L;
	Map<String, Object> parameters;

	MILCUser user;
	boolean filtered = false;
	boolean distinct = false;

	@Override
	public MILCSpecification<T> distinct() {
		distinct = true;
		return this;
	}

	@Override
	public MILCSpecification<T> withAccessFilter(){
		this.filtered = true;
		this.user = CurrentUserProvider.get();
		return this;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Map<String, Expression<String>> fieldMap = this.fieldMap(root);
		Predicate predicate = QueryPredicateBuilder.build(fieldMap, parameters);
		query.distinct(distinct);

		return filtered && user != null ?
				builder.and(predicate, this.filterPredicate(root, builder)) :
				predicate;
	}

	public abstract EntityGraph<T> entityGraph(EntityManager em);
	abstract Map<String, Expression<String>> fieldMap(Root<T> root);
	abstract Predicate filterPredicate(Root<T> root, CriteriaBuilder builder);

}
