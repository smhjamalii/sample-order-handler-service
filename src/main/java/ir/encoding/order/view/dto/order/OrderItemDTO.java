package ir.encoding.order.view.dto.order;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ir.encoding.order.types.order.OrderState;
import ir.encoding.order.view.dto.DTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItemDTO extends DTO {

	private Long id;
	private Long orderId;
	private Long productId;
	private String productName;
	private BigDecimal productPrice;
	@NotNull //TODO: range check, must be greater than zero
	private Integer amount;
	@NotNull
	private BigDecimal price;
	@NotNull
	private OrderState state;
	
}
