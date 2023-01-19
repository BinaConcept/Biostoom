package be.biostoom.certificate.util;

import be.biostoom.certificate.model.dto.DateRange;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
					.forEach(path -> predicates.add(getTypedPredicate(parameters.get("query"),"query", path)));

//					.forEach(path -> predicates.add(getTypedPredicate((String) parameters.get("query"),path.toString(), path)));

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
						predicates.add(getTypedPredicate(entry.getValue(), entry.getKey(), fieldMap.get(entry.getKey())))
				);

		return predicates.size() > 0 ?
				builder.and(predicates.toArray(new Predicate[0])) :
				builder.and();
	}

	private static Predicate getTypedPredicate(Object value, String key, Expression<?> path) {
		Class<?> clazz = value.getClass();
		log.trace("Parameter class is {}", clazz.toString());

		if(clazz.equals(String.class)) return stringLikePredicate((String) value, path);
		if(clazz.equals(Date.class)) return dateRangePredicate((Date) value, key, path);
		return objectEqualPredicate(value, path, clazz);
	}

	private static Predicate stringLikePredicate(String value, Expression<?> path){
		final String percentQuery = "%".concat(value.toLowerCase().concat("%"));
		return builder.like(builder.lower(path.as(String.class)), percentQuery);
	}

	private static Predicate dateRangePredicate(Date date, String key, Expression<?> path) {
		Predicate startPredicate,endPredicate;
		if(key.equals("startDate")){
			startPredicate = builder.greaterThanOrEqualTo(path.as(Date.class), date);
			return builder.and(startPredicate);
		}else {
			endPredicate = builder.lessThanOrEqualTo(path.as(Date.class), date);
			return builder.and(endPredicate);
		}
	}

	private static Predicate objectEqualPredicate(Object value, Expression<?> path, Class<?> clazz) {
		return builder.equal(path.as(clazz), value);
	}

}
