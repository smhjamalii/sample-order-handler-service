package ir.encoding.order.service.interfaces;

import java.util.Optional;

import ir.encoding.order.types.order.OrderState;
import ir.encoding.order.view.dto.order.OrderDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface OrderService {

	Optional<OrderDTO> findById(Long orderId);
	OrderDTO save(@Valid OrderDTO dto);
	OrderDTO edit(@Valid OrderDTO dto);
	void delete(@NotNull Long orderId); 
	void changeOrderState(OrderDTO dto, OrderState state);
}
