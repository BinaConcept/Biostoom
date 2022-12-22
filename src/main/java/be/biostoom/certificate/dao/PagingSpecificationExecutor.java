package eu.europa.ec.jrc.milc.dao;

import eu.europa.ec.jrc.milc.specification.MILCSpecification;
import eu.europa.ec.jrc.milc.utility.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityGraph;
import java.util.List;
import java.util.UUID;

public interface PagingSpecificationExecutor<T, ID> {
	PaginatedResponse<T> paginatedResponse(List<T> result, Long totalCount, Pageable pageRequest);
	Page<ID> idPage(Class<T> clazz, Specification<T> specification, Pageable pageRequest);
	PaginatedResponse<T> findAll(Class<T> clazz, MILCSpecification<T> specification, Pageable pageRequest);
}
