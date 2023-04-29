package ir.encoding.order.data.jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass @Getter @Setter
public class GenericEntity {

	private boolean enabled;
	private LocalDateTime createdAt;
	
}
