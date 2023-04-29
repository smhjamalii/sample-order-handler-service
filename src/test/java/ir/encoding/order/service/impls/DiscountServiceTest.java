package ir.encoding.order.service.impls;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import ir.encoding.order.service.interfaces.DiscountService;
import ir.encoding.order.view.dto.discount.DiscountDTO;

@SpringBootTest
public class DiscountServiceTest {

	@Autowired
	private DiscountService discountService;

	@Test
	public void shouldNotFindAnUnknownDiscount() {		
		Optional<DiscountDTO> persistedDiscount = discountService.findByCode("ABCD");
		Assert.isTrue(persistedDiscount.isEmpty(), "A discount with code ABCD does not exist");
	}	
	
	@Test
	public void shouldSaveANewDiscount() {
		DiscountDTO dto = DiscountDTO.builder()
				.code("ABCD770")
				.price(new BigDecimal(1500))
				.validationBeginDateTime(LocalDateTime.now())
				.validationEndDateTime(LocalDateTime.now().plusDays(5))
				.build();
		discountService.save(dto);
		
		Optional<DiscountDTO> persistedDiscount = discountService.findByCode("ABCD770");
		Assert.isTrue(persistedDiscount.isPresent(), "A discount with code ABCD770 should exists");
	}
	
	@Test
	public void shouldEditDiscountPrice() {
		DiscountDTO dto = DiscountDTO.builder()
				.code("ABCD880")
				.price(new BigDecimal(1500))
				.validationBeginDateTime(LocalDateTime.now())
				.validationEndDateTime(LocalDateTime.now().plusDays(5))
				.build();
		discountService.save(dto);
		
		dto.setPrice(new BigDecimal(555000));
		discountService.edit(dto);
		
		Optional<DiscountDTO> editedDiscount = discountService.findById(dto.getId());
				
		Assert.isTrue(editedDiscount.isPresent(), "A discount with code ABCD880 should exists");
		Assert.isTrue(editedDiscount.get().getPrice().intValue() == 555000, "Discount with code ABCD880 should have 555000 price");
	}
	
	@Test
	public void shouldDeleteAPersistedDiscount() {
		DiscountDTO dto = DiscountDTO.builder()
				.code("ABCD999")
				.price(new BigDecimal(1500))
				.validationBeginDateTime(LocalDateTime.now())
				.validationEndDateTime(LocalDateTime.now().plusDays(5))
				.build();
		discountService.save(dto);
						
		Optional<DiscountDTO> deletingDiscount = discountService.findById(dto.getId());
		discountService.delete(deletingDiscount.get().getId());
		
		Optional<DiscountDTO> deletedDiscount = discountService.findByCode("ABCD999"); 
				
		Assert.isTrue(deletedDiscount.isEmpty(), "A discount with code ABCD999 should not exists");		
	}
	
}
