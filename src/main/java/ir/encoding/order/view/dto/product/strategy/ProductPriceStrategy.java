package ir.encoding.order.view.dto.product.strategy;

import java.math.BigDecimal;

public interface ProductPriceStrategy {

	BigDecimal calculate(BigDecimal price);
	
}
