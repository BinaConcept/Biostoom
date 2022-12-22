package eu.europa.ec.jrc.milc.domain.dto.parameters;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Sort.Direction;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class BaseQueryParameters implements Serializable {
	
	private static final long serialVersionUID = -5816420476471375533L;
	String query;
	Integer take;
	Integer page;
	String sort;
	Direction direction;
	Map<String, Object> map = new HashMap<>();
	
	public void setQuery(String query) {
		map.put("query", query);
		this.query = query;
	}
	
	public void setTake(Integer take) {
		map.put("take", take);
		this.take = take;
	}
	
	public void setPage(Integer page) {
		map.put("page", page);
		this.page = page;
	}
	
	public void setSort(String sort) {
		map.put("sort", sort);
		this.sort = sort;
	}

	public void setDirection(Direction direction) {
		map.put("direction", direction);
		this.direction = direction;
	}

	public boolean isEmpty(){
		return map.size()==0;
	}

}
