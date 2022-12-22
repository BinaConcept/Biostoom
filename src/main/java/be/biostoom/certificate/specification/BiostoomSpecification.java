package be.biostoom.certificate.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

public interface BiostoomSpecification<T> extends Specification<T> {
	EntityGraph<T> entityGraph(EntityManager em);
	BiostoomSpecification<T> withAccessFilter();
	BiostoomSpecification<T> distinct();
}
