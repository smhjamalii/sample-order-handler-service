package ir.encoding.order.mappers;

import ir.encoding.order.data.jpa.entities.order.Order;
import ir.encoding.order.data.jpa.entities.order.OrderItem;
import ir.encoding.order.data.jpa.entities.product.Product;
import ir.encoding.order.view.dto.order.OrderItemDTO;

public class OrderItemMapper {
	
	public static OrderItemDTO getDTO(OrderItem orderItem) {
		OrderItemDTO dto = OrderItemDTO.builder()
				.id(orderItem.getId())
				.orderId(orderItem.getOrder().getId())
				.productId(orderItem.getProduct().getId())
				.productName(orderItem.getProduct().getName())
				.productPrice(orderItem.getProduct().getPrice())
				.amount(orderItem.getAmount())
				.price(orderItem.getPrice())
				.state(orderItem.getState())
				.build();
		
		dto.setEnabled(orderItem.isEnabled());
		dto.setCreatedAt(orderItem.getCreatedAt());
				
		return dto;
	}
	
	public static OrderItem getEntity(OrderItemDTO dto) {
		OrderItem entity = new OrderItem();
		entity.setId(dto.getId());
		entity.setOrder(new Order(dto.getOrderId()));
		entity.setProduct(new Product(dto.getProductId(), dto.getProductName(), dto.getProductPrice()));
		entity.setAmount(dto.getAmount());
		entity.setPrice(dto.getPrice());
		entity.setState(dto.getState());
		entity.setEnabled(dto.isEnabled());
		entity.setCreatedAt(dto.getCreatedAt());
		return entity;
	}
	
}
