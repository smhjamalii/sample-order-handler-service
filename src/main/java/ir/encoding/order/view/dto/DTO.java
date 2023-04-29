package ir.encoding.order.view.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DTO implements Serializable {

	private boolean enabled = true;
	private LocalDateTime createdAt = LocalDateTime.now();	
	
}
