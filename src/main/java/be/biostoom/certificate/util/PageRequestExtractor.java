package be.biostoom.certificate.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PageRequestExtractor {
	public static Pageable extract(Map<String, Object> parameters) {
		List<String> pagingAndSortingParameters = Arrays.asList("take","page","sort","direction");

		Integer take = (Integer) parameters.getOrDefault("take", 10);
		int page = parameters.containsKey("page") ?
				((Integer) parameters.get("page")) - 1 :
				(Integer) parameters.getOrDefault("page", 0);
		String sort = (String) parameters.get("sort");
		Direction direction = (Direction) parameters.getOrDefault("direction", Direction.ASC);

		parameters.keySet().removeIf(pagingAndSortingParameters::contains);

		return (sort == null) ?
				PageRequest.of(page, take) :
				PageRequest.of(page, take, Sort.by(direction, sort));
	}
}
