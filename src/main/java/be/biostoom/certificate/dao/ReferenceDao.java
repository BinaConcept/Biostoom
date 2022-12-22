package eu.europa.ec.jrc.milc.dao;

import java.util.Optional;

public interface ReferenceDao<T, ID> {
	Optional<T> getReference(Class<T> clazz, ID id);
}
