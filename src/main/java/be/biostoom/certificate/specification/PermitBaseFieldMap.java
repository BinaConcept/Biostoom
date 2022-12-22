package eu.europa.ec.jrc.milc.specification;

import eu.europa.ec.jrc.milc.domain.*;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.*;

public class PTRoundBaseFieldMap {
	public static Map<String, Expression<String>> getMap(Root<PTRound> root){
		Join<PTRound, PTScheme> scheme = root.join("ptScheme", JoinType.LEFT);
		Join<PTRound, Planning> planning = root.join("planning", JoinType.LEFT);
		Join<PTRound, MILCUser> coordinator = root.join("coordinator", JoinType.LEFT);

		Map<String, Expression<String>> fieldMap = new HashMap<>();
		fieldMap.put("name", root.get("name"));
		fieldMap.put("status", root.get("status"));
		fieldMap.put("type", root.get("type"));
		fieldMap.put("firstName", coordinator.get("firstName"));
		fieldMap.put("lastName", coordinator.get("lastName"));
		fieldMap.put("registrationRange", planning.get("registrationOpenDate"));
		fieldMap.put("reportingRange", planning.get("reportingOpenDate"));
		fieldMap.put("matrix", scheme.get("matrix"));
		fieldMap.put("ptScheme", scheme.get("name"));
		return fieldMap;
	}
	public static Predicate filterPredicate(Root<PTRound> root, CriteriaBuilder builder, MILCUser user) {
		Join<PTRound, MILCUserAccess> accesses = root.join("accesses");
		Join<MILCUserAccess, MILCUser> access = accesses.join("user");
		return builder.equal(access.get("id"), user.getId());
	}

	public static EntityGraph<PTRound> entityGraph(EntityManager em) {
		EntityGraph<PTRound> graph = em.createEntityGraph(PTRound.class);
		graph.addAttributeNodes("coordinator", "ptScheme", "planning");
		return graph;
	}
}
