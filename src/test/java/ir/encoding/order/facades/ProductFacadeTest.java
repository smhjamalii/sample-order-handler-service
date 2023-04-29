package ir.encoding.order.facades;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.lang.Assert;
import ir.encoding.order.service.interfaces.HighSeasonService;
import ir.encoding.order.types.product.ProductCategory;
import ir.encoding.order.view.dto.PagedResult;
import ir.encoding.order.view.dto.product.HighSeasonDTO;
import ir.encoding.order.view.dto.product.ProductDTO;
import ir.encoding.order.view.dto.product.ProductSearchDTO;

@SpringBootTest
public class ProductFacadeTest {

	@Autowired
	private HighSeasonService highSeasonService;	
	
	@Autowired
	private ProductFacade productFacade;
	
	private static final BigDecimal HIGH_SEASON_INFLATION = new BigDecimal(100);
	
	@Test
	public void shouldIncreaseProductPricesTemporarilyInHighSeasonPeriodByCategory() {
		var record1 = HighSeasonDTO.builder()
				.beginDateTime(LocalDateTime.now().minus(5L, ChronoUnit.SECONDS))
				.endDateTime(LocalDateTime.now().plus(5L, ChronoUnit.SECONDS))
				.category(ProductCategory.FOOD_BEVERAGE)
				.increaseAmount(HIGH_SEASON_INFLATION)
				.build();
		record1.setEnabled(true);
		record1.setCreatedAt(LocalDateTime.now());
		
		highSeasonService.save(record1);
		
		ProductSearchDTO ps = ProductSearchDTO.builder()
			.category(ProductCategory.FOOD_BEVERAGE)
			.build();
				
		PagedResult<ProductDTO> result = productFacade.search(ps, 0, 10);
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		PagedResult<ProductDTO> resultNoHighSeason = productFacade.search(ps, 0, 10);
		
		result.getResult().stream().forEach(p -> {
			resultNoHighSeason.getResult().stream()
				.filter(pp -> pp.getId().equals(p.getId()))
				.findFirst()
				.ifPresent(pp -> {
					Assert.isTrue( pp.getPrice().equals(p.getPrice().subtract(HIGH_SEASON_INFLATION)) );
				});
		});	
	}

	@Test
	public void shouldIncreaseProductPricesTemporarilyInHighSeasonPeriodByProductId() {
		var record1 = HighSeasonDTO.builder()
				.beginDateTime(LocalDateTime.now().minus(5L, ChronoUnit.SECONDS))
				.endDateTime(LocalDateTime.now().plus(5L, ChronoUnit.SECONDS))
				.productId(4L)
				.increaseAmount(HIGH_SEASON_INFLATION)
				.build();
		record1.setEnabled(true);
		record1.setCreatedAt(LocalDateTime.now());
		
		highSeasonService.save(record1);
		
		ProductSearchDTO ps = ProductSearchDTO.builder()			
			.build();
				
		PagedResult<ProductDTO> result = productFacade.search(ps, 0, 10);
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		PagedResult<ProductDTO> resultNoHighSeason = productFacade.search(ps, 0, 10);
		
		result.getResult().stream()
			.filter(productWithHs -> productWithHs.getId().equals(4L))
			.findFirst()
			.ifPresentOrElse(
					productWithHs -> {
						resultNoHighSeason.getResult().stream()
						.filter(pp -> pp.getId().equals(productWithHs.getId()))
						.findFirst()
						.ifPresent(pp -> {
							Assert.isTrue( pp.getPrice().equals(productWithHs.getPrice().subtract(HIGH_SEASON_INFLATION)) );
						});						
					}, 
					() -> Assert.isTrue(false, "Product with id 4 should be in the list"));
					
	}	
	
}
