package be.biostoom.certificate.specification;

import be.biostoom.certificate.model.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Map;

@Getter
@EqualsAndHashCode(callSuper = true)
public class EmployeeSpecification extends AbstractBiostoomSpecification<Employee> implements Serializable {
	private static final long serialVersionUID = -8953465453925321448L;

	private EmployeeSpecification(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public static EmployeeSpecification forParams(Map<String, Object> parameters){
		return new EmployeeSpecification(parameters);
	}

	@Override
	public EntityGraph<Employee> entityGraph(EntityManager em) {

		return EmployeeBaseFieldMap.entityGraph(em);
	}

	@Override
	Map<String, Expression<String>> fieldMap(Root<Employee> root) {
		Join<Employee, Company> company = root.join("company", JoinType.LEFT);

		Map<String, Expression<String>> fieldMap = EmployeeBaseFieldMap.getMap(root);
		fieldMap.put("name", company.get("name"));

		return fieldMap;
	}

	@Override
	Predicate filterPredicate(Root<Employee> root, CriteriaBuilder builder) {
		return EmployeeBaseFieldMap.filterPredicate(root, builder, this.company);

	}

}
