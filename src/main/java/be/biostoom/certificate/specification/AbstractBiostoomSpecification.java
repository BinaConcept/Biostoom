package be.biostoom.certificate.specification;

import be.biostoom.certificate.model.Company;
import be.biostoom.certificate.util.currentCompanyProvider;
import be.biostoom.certificate.util.QueryPredicateBuilder;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.Map;

public abstract class AbstractBiostoomSpecification<T> implements BiostoomSpecification<T> {

	private static final long serialVersionUID = 3736157462129598220L;
	Map<String, Object> parameters;

	Company company;
	boolean filtered = false;
	boolean distinct = false;

	@Override
	public BiostoomSpecification<T> distinct() {
		distinct = true;
		return this;
	}

	@Override
	public BiostoomSpecification<T> withAccessFilter(){
		this.filtered = true;
		Object companyId = parameters.get("companyId");
		this.company = currentCompanyProvider.getCompany((long)companyId);
		parameters.remove("companyId");
		return this;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Map<String, Expression<String>> fieldMap = this.fieldMap(root);
		Predicate predicate = QueryPredicateBuilder.build(fieldMap, parameters);
		query.distinct(distinct);

		return filtered && company != null ?
				builder.and(predicate, this.filterPredicate(root, builder)) :
				predicate;
	}

	public abstract EntityGraph<T> entityGraph(EntityManager em);
	abstract Map<String, Expression<String>> fieldMap(Root<T> root);
	abstract Predicate filterPredicate(Root<T> root, CriteriaBuilder builder);

}
