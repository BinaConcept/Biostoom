package be.biostoom.certificate.specification;

import be.biostoom.certificate.model.*;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.Map;

public class EmployeeBaseFieldMap {
	public static Map<String, Expression<String>> getMap(Root<Employee> root){
		Join<Employee, Company> company = root.join("company", JoinType.LEFT);
		Map<String, Expression<String>> fieldMap = new HashMap<>();
		fieldMap.put("name", company.get("name"));
		fieldMap.put("firstName", root.get("firstName"));
		fieldMap.put("lastName", root.get("lastName"));
		fieldMap.put("email", root.get("email"));
		fieldMap.put("gsm", root.get("gsm"));
		return fieldMap;
	}

	public static Predicate filterPredicate(Root<Employee> root, CriteriaBuilder builder, Company company) {

		Join<Permit, Company> accesses = root.join("company");
/*
		Join<Permit, Company> access = accesses.join("company");
*/
		if(company.getIsBioostoom()){
			return builder.equal(accesses.get("id"), accesses.get("id"));
		}

		return builder.equal(accesses.get("id"), company.getId());
	}

	public static EntityGraph<Employee> entityGraph(EntityManager em) {
		EntityGraph<Employee> graph = em.createEntityGraph(Employee.class);
		graph.addAttributeNodes("company");
		return graph;
	}
}
