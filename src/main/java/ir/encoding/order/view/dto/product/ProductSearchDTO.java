package ir.encoding.order.view.dto.product;

import java.io.Serializable;
import java.math.BigDecimal;

import ir.encoding.order.types.product.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class ProductSearchDTO implements Serializable {	
	
	private String name;
	private BigDecimal beginPrice;
	private BigDecimal endPrice;
	private String description;	
	private ProductCategory category;
	
}
