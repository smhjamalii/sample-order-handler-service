package ir.encoding.order.mappers;

import ir.encoding.order.data.jpa.entities.product.Product;
import ir.encoding.order.view.dto.product.ProductDTO;

public class ProductMapper {
	
	public static ProductDTO getDTO(Product product) {
		ProductDTO dto = ProductDTO.builder()
				.id(product.getId())
				.name(product.getName())
				.price(product.getPrice())
				.description(product.getDescription())
				.thumbnailImage(product.getThumbnailImage())
				.measurementType(product.getMeasurementType())
				.category(product.getCategory())
				.fragile(product.isFragile())
				.build();
		
		dto.setEnabled(product.isEnabled());
		dto.setCreatedAt(product.getCreatedAt());
		return dto;
	}
	
	public static Product getEntity(ProductDTO dto) {
		Product product = new Product();
		product.setId(dto.getId());
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setDescription(dto.getDescription());
		product.setThumbnailImage(dto.getThumbnailImage());
		product.setMeasurementType(dto.getMeasurementType());
		product.setCategory(dto.getCategory());
		product.setFragile(dto.isFragile());
		product.setCreatedAt(dto.getCreatedAt());
		product.setEnabled(dto.isEnabled());
		return product;
	}
	
}
