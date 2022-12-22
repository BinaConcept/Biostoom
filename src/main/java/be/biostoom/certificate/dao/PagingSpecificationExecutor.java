package be.biostoom.certificate.dao;

import be.biostoom.certificate.specification.BiostoomSpecification;
import be.biostoom.certificate.util.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PagingSpecificationExecutor<T, ID> {
	PaginatedResponse<T> paginatedResponse(List<T> result, Long totalCount, Pageable pageRequest);
	Page<ID> idPage(Class<T> clazz, Specification<T> specification, Pageable pageRequest);
	PaginatedResponse<T> findAll(Class<T> clazz, BiostoomSpecification<T> specification, Pageable pageRequest);
}
