package ir.encoding.order.view.dto.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ir.encoding.order.types.product.ProductCategory;
import ir.encoding.order.validation.groups.Delete;
import ir.encoding.order.validation.groups.Edit;
import ir.encoding.order.validation.highseason.CheckCategoryOrProductIdValue;
import ir.encoding.order.view.dto.DTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @Builder
@CheckCategoryOrProductIdValue
public class HighSeasonDTO extends DTO {

	@NotNull(groups = {Edit.class, Delete.class})
	private Long id;
	@NotNull
	private LocalDateTime beginDateTime;
	@NotNull
	private LocalDateTime endDateTime;
	private ProductCategory category;
	@NotNull
	private BigDecimal increaseAmount;
	private Long productId;		
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(beginDateTime, category, endDateTime, id, increaseAmount, productId);
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
		HighSeasonDTO other = (HighSeasonDTO) obj;
		return Objects.equals(beginDateTime, other.beginDateTime) && category == other.category
				&& Objects.equals(endDateTime, other.endDateTime) && Objects.equals(id, other.id)
				&& Objects.equals(increaseAmount, other.increaseAmount) && Objects.equals(productId, other.productId);
	}		
	
}
