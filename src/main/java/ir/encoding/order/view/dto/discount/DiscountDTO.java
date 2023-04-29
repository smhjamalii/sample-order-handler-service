package ir.encoding.order.view.dto.discount;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ir.encoding.order.view.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @Builder
public class DiscountDTO extends DTO {

	private Long id;
	private Long orderId;
	private BigDecimal orderPrice;
	private BigDecimal price;
	private BigDecimal maximumPrice;
	private Integer percent;		
	private String code;
	private LocalDateTime validationBeginDateTime;
	private LocalDateTime validationEndDateTime;	
	private Long userId;
}
