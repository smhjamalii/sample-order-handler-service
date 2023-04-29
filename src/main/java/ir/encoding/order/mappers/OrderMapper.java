package ir.encoding.order.mappers;

import ir.encoding.order.data.jpa.entities.order.Order;
import ir.encoding.order.data.jpa.entities.user.User;
import ir.encoding.order.view.dto.order.OrderDTO;

public class OrderMapper {
	
	public static OrderDTO getDTO(Order order) {				
		OrderDTO dto = OrderDTO.builder()
				.id(order.getId())
				.userId(order.getUser().getId())
				.state(order.getState())
				.registrationDateTme(order.getRegistrationDateTme())
				.modifyDateTme(order.getModifyDateTme())
				.build();
		dto.setEnabled(order.isEnabled());
		dto.setCreatedAt(order.getCreatedAt());
		return dto;
	}
	
	public static Order getEntity(OrderDTO dto) {
		Order order = new Order();
		order.setId(dto.getId());
		order.setUser(new User(dto.getUserId()));
		order.setState(dto.getState());
		order.setRegistrationDateTme(dto.getRegistrationDateTme());
		order.setModifyDateTme(dto.getModifyDateTme());
		order.setItems(dto.getItems().stream().map(OrderItemMapper::getEntity).toList());
		order.setDiscounts(dto.getDiscounts() != null ? dto.getDiscounts().stream().map(DiscountMapper::getEntity).toList() : null);
		order.setCreatedAt(dto.getCreatedAt());
		order.setEnabled(dto.isEnabled());
		return order;
	}
	
	
}
