package eu.europa.ec.jrc.milc.domain.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DateRange {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date end;
}
