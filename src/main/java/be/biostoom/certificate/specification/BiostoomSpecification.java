package eu.europa.ec.jrc.milc.specification;

import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public interface MILCSpecification<T> extends Specification<T> {
	EntityGraph<T> entityGraph(EntityManager em);
	MILCSpecification<T> withAccessFilter();
	MILCSpecification<T> distinct();
}
