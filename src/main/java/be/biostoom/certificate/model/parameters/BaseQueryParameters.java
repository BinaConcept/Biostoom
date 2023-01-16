package be.biostoom.certificate.model.parameters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.domain.Sort.Direction;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode
public class BaseQueryParameters implements Serializable {
	
	private static final long serialVersionUID = -5816420476471375533L;
	Integer take;
	Integer page;
	@JsonIgnore
	Map<String, Object> map = new HashMap<>();
	
	public void setTake(Integer take) {
		map.put("take", take);
		this.take = take;
	}
	
	public void setPage(Integer page) {
		map.put("page", page);
		this.page = page;
	}

	@JsonIgnore
	public boolean isEmpty(){
		return map.size()==0;
	}
}
