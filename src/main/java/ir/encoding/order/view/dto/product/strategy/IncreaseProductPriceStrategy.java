package ir.encoding.order.view.dto.product.strategy;

import java.math.BigDecimal;

import lombok.Getter;

public class IncreaseProductPriceStrategy implements ProductPriceStrategy {

	@Getter
	private BigDecimal value;		
	
	public IncreaseProductPriceStrategy(BigDecimal value) {		
		this.value = value;
	}

	public void addValue(BigDecimal value) {
		this.value = this.value.add(value);
	}
	
	@Override
	public BigDecimal calculate(BigDecimal price) {
		
		return price.add(value);
	}

}
