package ir.encoding.order.service.impls;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import ir.encoding.order.service.interfaces.OrderService;
import ir.encoding.order.service.interfaces.UserService;
import ir.encoding.order.types.order.OrderState;
import ir.encoding.order.view.dto.order.OrderDTO;
import ir.encoding.order.view.dto.order.OrderItemDTO;
import ir.encoding.order.view.dto.user.UserDTO;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest @Slf4j
public class OrderServiceTest {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void shouldNotSaveOrderWithTotalPriceLessThan50000() {
		
		UserDTO userDto = userService.findByUsername("admin");
		
		List<OrderItemDTO> items = new ArrayList<>();
		OrderItemDTO item1 = OrderItemDTO.builder()
				.amount(1)
				.productId(1L)
				.state(OrderState.CREATED)
				.build();
		
		OrderItemDTO item2 = OrderItemDTO.builder()
				.amount(1)
				.productId(2L)
				.state(OrderState.CREATED)
				.build();
		
		items.add(item1);
		items.add(item2);
		
		OrderDTO orderDto = OrderDTO.builder()
				.items(items)
				.registrationDateTme(LocalDateTime.now())
				.state(OrderState.CREATED)
				.userId(userDto.getId())
				.build();
		
		try {
			orderService.save(orderDto);
			Assert.isTrue(false, "Must throw a ConstraintViolationException");
		} catch(ConstraintViolationException e) {
			Assert.isTrue(! e.getMessage().contains("order.registration.time.is.between.8am.and.7pm"), "before running this test you need to Change your system time, set it between 8am and 7pm");
			Assert.isTrue(e.getMessage().contains("order.price.can.not.be.less.than.50.box"), "Should not save an order with price less than 50000");
		}
	}
	
	@Test
	public void shouldChangeOrderStateAndOrderItemsState() {
		UserDTO userDto = userService.findByUsername("admin");
		
		List<OrderItemDTO> items = new ArrayList<>();
		OrderItemDTO item1 = OrderItemDTO.builder()
				.amount(100000)
				.productId(1L)
				.state(OrderState.CREATED)
				.build();
		
		OrderItemDTO item2 = OrderItemDTO.builder()
				.amount(1)
				.productId(2L)
				.state(OrderState.CREATED)
				.build();
		
		items.add(item1);
		items.add(item2);
		
		OrderDTO orderDto = OrderDTO.builder()
				.items(items)
				.registrationDateTme(LocalDateTime.now())
				.state(OrderState.CREATED)
				.userId(userDto.getId())
				.build();

		orderDto = orderService.save(orderDto);
		
		Optional<OrderDTO> persistedDto = orderService.findById(orderDto.getId());
		if(persistedDto.isPresent()) {
			OrderDTO dto = persistedDto.get();
			orderService.changeOrderState(dto, OrderState.CONFIRMED);
			persistedDto = orderService.findById(orderDto.getId());
			
			Assert.isTrue(persistedDto.get().getState().equals(OrderState.CONFIRMED), "Order state should be CONFIRMED");
			persistedDto.get().getItems().stream().forEach(item -> {
				Assert.isTrue(item.getState().equals(OrderState.CONFIRMED), "Order item state should be CONFIRMED");				
			});
		}
	}
	
}
