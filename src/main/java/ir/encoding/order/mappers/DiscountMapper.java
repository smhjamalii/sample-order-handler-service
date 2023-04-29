package ir.encoding.order.mappers;

import ir.encoding.order.data.jpa.entities.discount.Discount;
import ir.encoding.order.data.jpa.entities.order.Order;
import ir.encoding.order.data.jpa.entities.user.User;
import ir.encoding.order.view.dto.discount.DiscountDTO;

public class DiscountMapper {

	public static DiscountDTO getDTO(Discount discount) {		
		DiscountDTO dto = DiscountDTO.builder()
				.id(discount.getId())
				.orderId(discount.getOrder() != null ? discount.getOrder().getId() : null)				
				.price(discount.getPrice())
				.maximumPrice(discount.getMaximumPrice())
				.code(discount.getCode())
				.validationBeginDateTime(discount.getValidationBeginDateTime())
				.validationEndDateTime(discount.getValidationEndDateTime())
				.userId(discount.getUser() != null ? discount.getUser().getId() : null)
				.build();
		
		dto.setCreatedAt(discount.getCreatedAt());
		dto.setEnabled(discount.isEnabled());
		
		return dto;
	}
	
	public static Discount getEntity(DiscountDTO dto) {
		Discount discount = new Discount();
		discount.setId(dto.getId());
		discount.setOrder(dto.getOrderId() != null ? new Order(dto.getOrderId()) : null);
		discount.setPrice(dto.getPrice());
		discount.setMaximumPrice(dto.getMaximumPrice());
		discount.setCreatedAt(dto.getCreatedAt());
		discount.setEnabled(dto.isEnabled());
		discount.setCode(dto.getCode());
		discount.setValidationBeginDateTime(dto.getValidationBeginDateTime());
		discount.setValidationEndDateTime(dto.getValidationEndDateTime());
		discount.setUser(dto.getUserId() != null ? new User(dto.getUserId()) : null);
		return discount;
	}
	
}
