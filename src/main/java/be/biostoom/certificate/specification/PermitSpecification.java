package eu.europa.ec.jrc.milc.specification;

import eu.europa.ec.jrc.milc.domain.PTRound;
import eu.europa.ec.jrc.milc.domain.PTScheme;
import org.springframework.stereotype.Component;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PTRoundOverviewSpecification extends AbstractMILCSpecification<PTRound> {
	private static final long serialVersionUID = -8953465453925321448L;

	private PTRoundOverviewSpecification(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public static PTRoundOverviewSpecification forParams(Map<String, Object> parameters){
		return new PTRoundOverviewSpecification(parameters);
	}

	@Override
	public EntityGraph<PTRound> entityGraph(EntityManager em) {
		return PTRoundBaseFieldMap.entityGraph(em);
	}

	@Override
	Map<String, Expression<String>> fieldMap(Root<PTRound> root) {
		Join<PTRound, PTScheme> scheme = root.join("ptScheme", JoinType.LEFT);

		Map<String, Expression<String>> fieldMap = PTRoundBaseFieldMap.getMap(root);
		fieldMap.put("ptScheme", scheme.get("name"));

		return fieldMap;
	}

	@Override
	Predicate filterPredicate(Root<PTRound> root, CriteriaBuilder builder) {
		return PTRoundBaseFieldMap.filterPredicate(root, builder, this.user);
	}

}
