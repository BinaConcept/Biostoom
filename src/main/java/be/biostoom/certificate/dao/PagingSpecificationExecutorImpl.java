package eu.europa.ec.jrc.milc.dao;

import eu.europa.ec.jrc.milc.specification.MILCSpecification;
import eu.europa.ec.jrc.milc.utility.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class PagingSpecificationExecutorImpl<T, ID> implements PagingSpecificationExecutor<T, ID> {

	private final EntityManager em;
	private final CriteriaBuilder builder;

	public PagingSpecificationExecutorImpl(EntityManager em) {
		this.em = em;
		this.builder = em.getCriteriaBuilder();
	}

	@Override
	public PaginatedResponse<T> paginatedResponse(List<T> result, Long totalCount, Pageable pageRequest) {
		PaginatedResponse<T> response = new PaginatedResponse<>();
		response.setEntities(result);
		response.setPage(pageRequest.getPageNumber());
		response.setSize(pageRequest.getPageSize());
		response.setTotal(totalCount);
		response.setPageRequest(pageRequest);

		return response;
	}

	@Override
	public Page<ID> idPage(Class<T> clazz, Specification<T> specification, Pageable pageRequest) {
		CriteriaQuery<Object> query = builder.createQuery(Object.class);
		Root<T> root = query.from(clazz);
		Predicate predicate = specification.toPredicate(root, query, builder);
		query
				.select(root.get("id"))
				.where(predicate)
				.orderBy(QueryUtils.toOrders(pageRequest.getSort(), root, builder))
				.groupBy(root.get("id"));

		int first = pageRequest.getPageNumber() * (pageRequest.getPageSize() - 1);
		List<Object> arrayResult = em
				.createQuery(query)
				.setMaxResults(pageRequest.getPageSize())
				.setFirstResult(first)
				.getResultList();
		Long count = this.getCount(clazz, specification);
		List<ID> idList = arrayResult
				.stream()
				.map(obj -> (ID) obj)
				.collect(Collectors.toList());
		return new PageImpl<>(idList, pageRequest, count);
	}

	@Override
	public PaginatedResponse<T> findAll(Class<T> clazz, MILCSpecification<T> specification, Pageable pageRequest) {
		Page<ID> idPage = this.idPage(clazz, specification, pageRequest);

		CriteriaQuery<T> query = builder.createQuery(clazz);
		Root<T> root = query.from(clazz);

		CriteriaBuilder.In<ID> inClause = builder.in(root.get("id"));
		for(ID id: idPage) {
			inClause.value(id);
		}

		query.where(inClause);

		List<T> entities = em.createQuery(query)
				.setHint("javax.persistence.fetchgraph", specification.entityGraph(em))
				.getResultList();
		return this.paginatedResponse(entities, idPage.getTotalElements(), idPage.getPageable());
	}

	Long getCount(Class<T> clazz, Specification<T> specification) {
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<T> root = query.from(clazz);
		Predicate predicate = specification.toPredicate(root, query, builder);
		query.select(builder.countDistinct(root)).where(predicate);
		return em
				.createQuery(query)
				.getSingleResult();
	}

}
