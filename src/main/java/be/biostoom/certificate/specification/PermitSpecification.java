package be.biostoom.certificate.specification;

import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.model.StartPermit;
import be.biostoom.certificate.model.StopPermit;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Map;

@Getter
@EqualsAndHashCode(callSuper = true)
public class PermitSpecification extends AbstractBiostoomSpecification<Permit> implements Serializable {
	private static final long serialVersionUID = -8953465453925321448L;

	private PermitSpecification(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public static PermitSpecification forParams(Map<String, Object> parameters){
		return new PermitSpecification(parameters);
	}

	@Override
	public EntityGraph<Permit> entityGraph(EntityManager em) {
		return PermitBaseFieldMap.entityGraph(em);
	}

	@Override
	Map<String, Expression<String>> fieldMap(Root<Permit> root) {
		Join<Permit, StartPermit> start = root.join("startPermits", JoinType.LEFT);
		Join<Permit, StopPermit> stop = root.join("stopPermits", JoinType.LEFT);

		Map<String, Expression<String>> fieldMap = PermitBaseFieldMap.getMap(root);
		fieldMap.put("startDate", start.get("startDate"));
		fieldMap.put("endDate", stop.get("endDate"));

		return fieldMap;
	}

	@Override
	Predicate filterPredicate(Root<Permit> root, CriteriaBuilder builder) {
		return PermitBaseFieldMap.filterPredicate(root, builder, this.company);

	}

}
