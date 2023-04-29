package ir.encoding.order.view.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PagedResult<T extends DTO> implements Serializable {

	private List<T> result;
	private Long totalCount;
	
	public PagedResult(List<T> result, Long totalCount) {		
		this.result = result;
		this.totalCount = totalCount;
	}
		
}
