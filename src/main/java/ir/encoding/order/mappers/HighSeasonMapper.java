package ir.encoding.order.mappers;

import ir.encoding.order.data.jpa.entities.highseason.HighSeason;
import ir.encoding.order.data.jpa.entities.product.Product;
import ir.encoding.order.view.dto.product.HighSeasonDTO;

public class HighSeasonMapper {	
		
	public static HighSeasonDTO getDTO(HighSeason highSeason) {
		HighSeasonDTO dto = HighSeasonDTO.builder()
				.id(highSeason.getId())
				.beginDateTime(highSeason.getBeginDateTime())
				.endDateTime(highSeason.getEndDateTime())
				.category(highSeason.getCategory())
				.increaseAmount(highSeason.getIncreaseAmount())
				.productId(highSeason.getProduct() != null ? highSeason.getProduct().getId() : null)				
				.build();
		
		dto.setCreatedAt(highSeason.getCreatedAt());
		dto.setEnabled(highSeason.isEnabled());
		return dto;
				
	}
		
	public static HighSeason getEntity(HighSeasonDTO dto) {
		HighSeason highSeason = new HighSeason();
		highSeason.setId(dto.getId());
		highSeason.setBeginDateTime(dto.getBeginDateTime());
		highSeason.setEndDateTime(dto.getEndDateTime());
		highSeason.setCategory(dto.getCategory());
		highSeason.setIncreaseAmount(dto.getIncreaseAmount());
		if(dto.getProductId() != null) {
			highSeason.setProduct(new Product(dto.getProductId()));
		}
		highSeason.setEnabled(dto.isEnabled());
		highSeason.setCreatedAt(dto.getCreatedAt());
		return highSeason;
	}
	
}
