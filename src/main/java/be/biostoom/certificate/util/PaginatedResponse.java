package eu.europa.ec.jrc.milc.utility;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse<T> implements Serializable, Iterable<T>{

	public PaginatedResponse(List<T> list){
		this.entities = list;
		this.size = list.size();
	}
	public PaginatedResponse(Page<T> page){
		this.entities = page.getContent();
		this.size = page.getContent().size();
		this.page = page.getNumber();
		this.total = page.getTotalElements();
		this.pageRequest = page.getPageable();
	}
	public <R> PaginatedResponse(PaginatedResponse<R> paginatedResponse) {
		this.size = paginatedResponse.getSize();
		this.page = paginatedResponse.getPage();
		this.total = paginatedResponse.getTotal();
		this.pageRequest = paginatedResponse.getPageRequest();
	}

	private static final long serialVersionUID = 2793833283281817389L;
	List<T> entities;
	Number total;
	Number size;
	Number page;

	@JsonIgnore
	Pageable pageRequest;

	public Number getTotalPages() {
		return size.equals(0) ? 1 : (int) Math.ceil(total.doubleValue() / size.doubleValue());
	}

	public boolean isLast() {
		return getTotalPages().intValue() >= total.intValue();
	}

	public <R> PaginatedResponse<R> mapTo(Function<T, R> mappingFunction) {
		PaginatedResponse<R> response = new PaginatedResponse<>();
		response.setTotal(total);
		response.setSize(size);
		response.setPage(page);
		response.setPageRequest(pageRequest);
		response.setEntities(entities.stream().map(mappingFunction).collect(Collectors.toList()));
		return response;
	}

	public Stream<T> stream() {
		return entities.stream();
	}

	public void forEach(Consumer<? super T> consumer) {
		entities.forEach(consumer);
	}

	public PaginatedResponse<T> peek(Consumer<? super T> consumer) {
		entities.forEach(consumer);
		return this;
	}

	@Override
	public Iterator<T> iterator() {
		return entities.iterator();
	}

	public void add(T item) {
		entities.add(item);
		size = size.intValue() + 1;
	}
	public void addAll(Collection<T> items) {
		entities.addAll(items);
		size = size.intValue() + items.size();
	}

	public static <T> Collector<T, PaginatedResponse<T>, PaginatedResponse<T>> collector(Pageable pageRequest) {
		return Collector.of(
				() -> {
					PaginatedResponse<T> response = new PaginatedResponse<>();
					response.setPageRequest(pageRequest);
					response.setPage(pageRequest.getPageNumber());
					return response;
				},
				PaginatedResponse::add,
				(response1, response2) -> {
					response1.addAll(response2.getEntities());
					return response1;
				},
				response -> {
					int startIndex = pageRequest.getPageSize() * pageRequest.getPageNumber();
					int endIndex = pageRequest.getPageSize() * (pageRequest.getPageNumber() + 1);
					List<T> paged = new ArrayList<>(response.getEntities().subList(startIndex, endIndex));
					response.setTotal(response.getEntities().size());
					response.setSize(paged.size());
					response.setEntities(paged);
					return response;
				}
		);
	}
}
