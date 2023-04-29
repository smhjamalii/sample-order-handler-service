package ir.encoding.order.service.impls;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import ir.encoding.order.service.interfaces.HighSeasonService;
import ir.encoding.order.types.product.ProductCategory;
import ir.encoding.order.view.dto.product.HighSeasonDTO;
import ir.encoding.order.view.dto.product.HighSeasonSearchDTO;

@SpringBootTest
@ActiveProfiles("test")
public class HighSeasonServiceTest {

	@Autowired
	private HighSeasonService highSeasonService;	
	
	@Test
	public void shouldSaveANewHighSeason() {
		var dto = new HighSeasonDTO();
		dto.setBeginDateTime(LocalDateTime.now());
		dto.setEndDateTime(LocalDateTime.now().plus(2L, ChronoUnit.MINUTES));
		dto.setCategory(ProductCategory.SPORT);
		dto.setIncreaseAmount(new BigDecimal(100));																	
		dto.setEnabled(true);
		dto.setCreatedAt(LocalDateTime.now());
		dto = highSeasonService.save(dto);
		
		Assert.notNull(dto.getId(), "After persisting an entity, its id must not be null");								
	}
	
	@Test
	public void shouldEditAHighSeason() {
		var dto = new HighSeasonDTO();
		dto.setBeginDateTime(LocalDateTime.now());
		dto.setEndDateTime(LocalDateTime.now().plus(2L, ChronoUnit.MINUTES));
		dto.setCategory(ProductCategory.SPORT);
		dto.setIncreaseAmount(new BigDecimal(100));																	
		dto.setEnabled(true);
		dto.setCreatedAt(LocalDateTime.now());
		dto = highSeasonService.save(dto);
		
		var persistedDto = highSeasonService.findById(dto.getId()).get();
		persistedDto.setCategory(ProductCategory.APPAREL);
		dto = highSeasonService.edit(persistedDto);
		
		Assert.isTrue(dto.equals(persistedDto), "Edited dto must have the same value");
	}	
	
	@Test
	public void shouldDeleteAHighSeason() {
		var dto = new HighSeasonDTO();
		dto.setBeginDateTime(LocalDateTime.now());
		dto.setEndDateTime(LocalDateTime.now().plus(2L, ChronoUnit.MINUTES));
		dto.setCategory(ProductCategory.SPORT);
		dto.setIncreaseAmount(new BigDecimal(100));																	
		dto.setEnabled(true);
		dto.setCreatedAt(LocalDateTime.now());
		dto = highSeasonService.save(dto);
		
		boolean result = highSeasonService.delete(dto.getId());
		Assert.isTrue(result, "A successfull delete operation resturns true");
		var deletedDto = highSeasonService.findById(dto.getId());
		Assert.isTrue(deletedDto.isEmpty(), "finding a deleted record should return empty result");
	}
	
	@Test
	public void shouldSearchCorrectly() {		
		var record1 = HighSeasonDTO.builder()
				.beginDateTime(LocalDateTime.now())
				.endDateTime(LocalDateTime.now().plus(2L, ChronoUnit.MINUTES))
				.category(ProductCategory.FOOD_BEVERAGE)
				.increaseAmount(new BigDecimal(10))
				.build();
		record1.setEnabled(true);
		record1.setCreatedAt(LocalDateTime.now());
		highSeasonService.save(record1);
		
		var record2 = HighSeasonDTO.builder()
				.beginDateTime(LocalDateTime.now())
				.endDateTime(LocalDateTime.now().plus(2L, ChronoUnit.MINUTES))
				.category(ProductCategory.FOOD_BEVERAGE)
				.increaseAmount(new BigDecimal(20))
				.build();
		record2.setEnabled(true);
		record2.setCreatedAt(LocalDateTime.now());
		highSeasonService.save(record2);
		
		HighSeasonSearchDTO criteria = new HighSeasonSearchDTO();
		
		List<HighSeasonDTO> searchResultForAll = highSeasonService.search(criteria);
		Assert.isTrue(searchResultForAll.stream().filter(h -> h.getCategory().equals(ProductCategory.FOOD_BEVERAGE)).count() == 2, "Two records in category FOOD_BEVERAGE should be found");
		
		List<ProductCategory> cats = Collections.singletonList(ProductCategory.SPORT);
		criteria.setCategories(cats);
		List<HighSeasonDTO> searchResultForSportCategory = highSeasonService.search(criteria);		
		Assert.isTrue(searchResultForSportCategory.stream().filter(p -> p.getCategory().equals(ProductCategory.SPORT)).count() == searchResultForSportCategory.size(), "List must include only products in sport category");		
	}
}
