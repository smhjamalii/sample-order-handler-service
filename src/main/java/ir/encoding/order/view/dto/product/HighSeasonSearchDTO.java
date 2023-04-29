package ir.encoding.order.view.dto.product;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import ir.encoding.order.types.product.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor @AllArgsConstructor @Builder
public class HighSeasonSearchDTO implements Serializable {

	private LocalDateTime beginDateTime;
	private LocalDateTime endDateTime;
	private List<ProductCategory> categories;
	private List<Long> productIds;
	
}
