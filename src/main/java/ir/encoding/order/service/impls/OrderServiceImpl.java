package ir.encoding.order.service.impls;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ir.encoding.order.data.jpa.entities.discount.Discount;
import ir.encoding.order.data.jpa.entities.order.Order;
import ir.encoding.order.data.jpa.entities.order.OrderItem;
import ir.encoding.order.data.jpa.entities.product.Product;
import ir.encoding.order.data.jpa.repositories.order.OrderItemRepository;
import ir.encoding.order.data.jpa.repositories.order.OrderRepository;
import ir.encoding.order.data.jpa.repositories.product.ProductRepository;
import ir.encoding.order.data.jpa.repositories.user.UserRepository;
import ir.encoding.order.mappers.DiscountMapper;
import ir.encoding.order.mappers.OrderItemMapper;
import ir.encoding.order.mappers.OrderMapper;
import ir.encoding.order.service.interfaces.OrderService;
import ir.encoding.order.types.order.OrderState;
import ir.encoding.order.view.dto.order.OrderDTO;
import ir.encoding.order.view.dto.order.OrderItemDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderItemRepository orderItemRepo;
	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public Optional<OrderDTO> findById(Long orderId) {		
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isPresent()) {
			Order order = optionalOrder.get();
			
			BigDecimal totalDeiscount = order.getDiscounts() != null && ! order.getDiscounts().isEmpty() ? 
					order.getDiscounts().stream().map(Discount::getPrice).reduce(BigDecimal::add).get() : 
						BigDecimal.ZERO;
			
			List<OrderItem> items = orderItemRepo.findByOrderId(order.getId());
			BigDecimal orderItemSum = items != null && ! items.isEmpty() ? 
											items.stream()
											.map(item -> item.getPrice().multiply(new BigDecimal(item.getAmount())))
											.reduce(BigDecimal::add).get() : 
										BigDecimal.ZERO;			
			
			OrderDTO dto = OrderMapper.getDTO(order);
			dto.setItems(items.stream().map(OrderItemMapper::getDTO).toList());
			dto.setDiscounts(order.getDiscounts() != null ? order.getDiscounts().stream().map(DiscountMapper::getDTO).toList() : null);			
			dto.setTotalDiscounts(totalDeiscount);
			dto.setTotalPrice(orderItemSum.subtract(totalDeiscount));						
			return Optional.of(dto);
		} else {
			return Optional.empty();
		}		
	}

	private void setOrderItemPrices(OrderDTO dto, Order persistedOrder) {
		List<Long> productIds = dto.getItems().stream().map(OrderItemDTO::getProductId).toList();
		List<Product> products = productRepo.findByIds(productIds);
		persistedOrder.getItems().stream().forEach(item -> {
			Product prd = products.stream().filter(p -> p.getId().equals(item.getProduct().getId())).findFirst().get();
			item.setOrder(persistedOrder);
			item.setProduct(prd);
			item.setPrice(prd.getPrice());
		});		
	}
	
	@Override
	public OrderDTO save(@Valid OrderDTO dto) {
		
		Order order = OrderMapper.getEntity(dto);
		order.setUser(userRepository.getReferenceById(dto.getUserId()));
		Order persistedOrder = orderRepository.save(order);
		
		setOrderItemPrices(dto, persistedOrder);		
		orderItemRepo.saveAll(persistedOrder.getItems());
		
		dto.setId(persistedOrder.getId());
		return dto;
	}	
	
	@Override
	public OrderDTO edit(@Valid OrderDTO dto) {
		Order order = OrderMapper.getEntity(dto);
		order = orderRepository.save(order);		
		return OrderMapper.getDTO(order);
	}

	@Override
	public void delete(@NotNull Long orderId) {
		orderRepository.deleteById(orderId);
	}

	@Override
	public void changeOrderState(OrderDTO dto, OrderState state) {		
		dto.setState(state);
		dto.getItems().stream().forEach(item -> item.setState(state));
		orderRepository.save(OrderMapper.getEntity(dto));
	}

}
