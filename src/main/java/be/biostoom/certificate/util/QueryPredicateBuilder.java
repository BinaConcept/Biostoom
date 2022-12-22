package eu.europa.ec.jrc.milc.utility;

import eu.europa.ec.jrc.milc.domain.dto.DateRange;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.*;

@Component
@Log4j2
public class QueryPredicateBuilder {

	private static CriteriaBuilder builder;

	public QueryPredicateBuilder(EntityManager em) {
		builder = em.getCriteriaBuilder();
	}

	public static Predicate build(Map<String, Expression<String>> fieldMap, Map<String, Object> parameters) {
		Predicate userQueryPredicate = queryPredicate(fieldMap, parameters);
		Predicate filterPredicate = filterPredicate(fieldMap, parameters);

		return builder.and(userQueryPredicate, filterPredicate);
	}

	private static Predicate queryPredicate(Map<String, Expression<String>> fieldMap, Map<String, Object> parameters) {
		List<Predicate> predicates = new ArrayList<>();
		if(parameters.containsKey("query"))
			fieldMap
					.values()
					.forEach(path -> predicates.add(getTypedPredicate((String) parameters.get("query"), path)));

		return predicates.size() > 0 ?
				builder.or(predicates.toArray(new Predicate[0])) :
				builder.and();
	}

	private static Predicate filterPredicate(Map<String, Expression<String>> fieldMap, Map<String, Object> parameters) {
		List<Predicate> predicates = new ArrayList<>();
		parameters
				.entrySet()
				.stream()
				.filter(entry -> !entry.getKey().equals("query"))
				.forEach(entry ->
						predicates.add(getTypedPredicate(entry.getValue(), fieldMap.get(entry.getKey())))
				);

		return predicates.size() > 0 ?
				builder.and(predicates.toArray(new Predicate[0])) :
				builder.and();
	}

	private static Predicate getTypedPredicate(Object value, Expression<?> path) {
		Class<?> clazz = value.getClass();
		log.trace("Parameter class is {}", clazz.toString());

		if(clazz.equals(String.class)) return stringLikePredicate((String) value, path);
		if(clazz.equals(DateRange.class)) return dateRangePredicate((DateRange) value, path);
		return objectEqualPredicate(value, path, clazz);
	}

	private static Predicate stringLikePredicate(String value, Expression<?> path){
		final String percentQuery = "%".concat(value.toLowerCase().concat("%"));
		return builder.like(builder.lower(path.as(String.class)), percentQuery);
	}

	private static Predicate dateRangePredicate(DateRange range, Expression<?> path) {
		Predicate startPredicate = builder.greaterThanOrEqualTo(path.as(Date.class), range.getStart());
		Predicate endPredicate = builder.lessThanOrEqualTo(path.as(Date.class), range.getEnd());
		return builder.and(startPredicate, endPredicate);
	}

	private static Predicate objectEqualPredicate(Object value, Expression<?> path, Class<?> clazz) {
		return builder.equal(path.as(clazz), value);
	}

}
