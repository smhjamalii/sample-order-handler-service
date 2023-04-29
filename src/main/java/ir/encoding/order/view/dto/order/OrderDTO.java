package ir.encoding.order.view.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ir.encoding.order.types.order.OrderState;
import ir.encoding.order.validation.groups.Edit;
import ir.encoding.order.validation.order.CheckMinimumOrderPrice;
import ir.encoding.order.validation.order.CheckOrderRegistrationDate;
import ir.encoding.order.view.dto.DTO;
import ir.encoding.order.view.dto.discount.DiscountDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @Builder
@CheckOrderRegistrationDate
@CheckMinimumOrderPrice
public class OrderDTO extends DTO {

	@NotNull(groups = {Edit.class})
	private Long id;
	private Long userId;	
	private List<OrderItemDTO> items;
	private OrderState state;
	private List<DiscountDTO> discounts;
	private LocalDateTime registrationDateTme;
	private LocalDateTime modifyDateTme;
	private BigDecimal totalDiscounts;
	private BigDecimal totalPrice;
	
}
