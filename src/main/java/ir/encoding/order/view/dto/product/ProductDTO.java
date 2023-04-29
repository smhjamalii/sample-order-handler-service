package ir.encoding.order.view.dto.product;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ir.encoding.order.types.product.MeasurementType;
import ir.encoding.order.types.product.ProductCategory;
import ir.encoding.order.view.dto.DTO;
import ir.encoding.order.view.dto.product.strategy.ProductPriceStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @Builder
public class ProductDTO extends DTO {

	private Long id;
	private String name;
	private BigDecimal price;
	private String description;
	private String thumbnailImage;
	private MeasurementType measurementType;	
	private ProductCategory category;
	private boolean fragile;
	
	@Builder.Default
	private ProductPriceStrategy productPriceStrategy = price -> price;
	
	public BigDecimal getPrice() {
		return productPriceStrategy.calculate(price);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(category, description, id, measurementType, name, price, thumbnailImage);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDTO other = (ProductDTO) obj;
		return category == other.category && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && measurementType == other.measurementType
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price)
				&& Objects.equals(thumbnailImage, other.thumbnailImage);
	}
	
}
