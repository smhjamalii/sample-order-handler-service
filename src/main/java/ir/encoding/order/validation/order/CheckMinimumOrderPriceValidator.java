package ir.encoding.order.validation.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ir.encoding.order.service.interfaces.ProductService;
import ir.encoding.order.view.dto.order.OrderDTO;
import ir.encoding.order.view.dto.order.OrderItemDTO;
import ir.encoding.order.view.dto.product.ProductDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckMinimumOrderPriceValidator implements ConstraintValidator<CheckMinimumOrderPrice, OrderDTO> {

	private static final BigDecimal MINIMUM_ORDER_PRICE = new BigDecimal(50_000);
	
	@Autowired
	private ProductService productService;
	
	@Override
	public boolean isValid(OrderDTO order, ConstraintValidatorContext context) {
		if(order.getItems() == null || order.getItems().isEmpty()) {
			
			return false;
			
		} else {
			List<Long> productIds = order.getItems().stream().map(item -> item.getProductId()).toList();
			List<ProductDTO> products = productService.findByIds(productIds);
			BigDecimal totalOrderPrice = BigDecimal.ZERO;
			for(OrderItemDTO item : order.getItems()) {
				ProductDTO prdct = products.stream().filter(p -> p.getId().equals(item.getProductId())).findFirst().get();
				totalOrderPrice = totalOrderPrice.add(prdct.getPrice().multiply(new BigDecimal(item.getAmount())));
			}
			return totalOrderPrice.compareTo(MINIMUM_ORDER_PRICE) >= 0;
		}			
	}

}
