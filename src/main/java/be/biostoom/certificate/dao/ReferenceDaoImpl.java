package eu.europa.ec.jrc.milc.dao;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ReferenceDaoImpl<T, ID> implements ReferenceDao<T, ID> {
	private final EntityManager em;

	public ReferenceDaoImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Optional<T> getReference(Class<T> clazz, ID id) {
		return Optional.ofNullable(em.getReference(clazz, id));
	}
}
