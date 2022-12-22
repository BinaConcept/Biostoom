package be.biostoom.certificate.specification;

import be.biostoom.certificate.model.Company;
import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.model.StartPermit;
import be.biostoom.certificate.model.StopPermit;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.Map;

public class PermitBaseFieldMap {
	public static Map<String, Expression<String>> getMap(Root<Permit> root){
		Join<Permit, StartPermit> start = root.join("startPermits", JoinType.LEFT);
		Join<Permit, StopPermit> stop = root.join("stopPermits", JoinType.LEFT);

		Map<String, Expression<String>> fieldMap = new HashMap<>();
		fieldMap.put("status", root.get("status"));
		fieldMap.put("startDate", start.get("startDate"));
		fieldMap.put("endDate", stop.get("endDate"));

		return fieldMap;
	}

	public static Predicate filterPredicate(Root<Permit> root, CriteriaBuilder builder, Company company) {

		Join<Permit, Company> accesses = root.join("company");
/*
		Join<Permit, Company> access = accesses.join("company");
*/
		if(company.getIsBioostoom()){
			return builder.equal(accesses.get("id"), accesses.get("id"));
		}

		return builder.equal(accesses.get("id"), company.getId());
	}

	public static EntityGraph<Permit> entityGraph(EntityManager em) {
		EntityGraph<Permit> graph = em.createEntityGraph(Permit.class);
		graph.addAttributeNodes("startPermits", "stopPermits");
		return graph;
	}
}
