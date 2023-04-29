package ir.encoding.order.service.impls;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ir.encoding.order.data.jpa.entities.discount.Discount;
import ir.encoding.order.data.jpa.repositories.discount.DiscountRepository;
import ir.encoding.order.mappers.DiscountMapper;
import ir.encoding.order.service.interfaces.DiscountService;
import ir.encoding.order.view.dto.discount.DiscountDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountRepository discountRepo;
	
	@Override
	public Optional<DiscountDTO> findById(Long discountId) {
		Optional<Discount> discount = discountRepo.findById(discountId);
		if(discount.isPresent()) {
			DiscountDTO dto = DiscountMapper.getDTO(discount.get());
			dto.setOrderPrice(calculateOrderPrice(discount.get()));
			dto.setPercent(calculateDiscountPercentage(discount.get().getPrice(), dto.getOrderPrice()));			
			return Optional.of(dto);
		} else
			return Optional.empty();
	}

	@Override
	public Optional<DiscountDTO> findByCode(String code) {
		Discount discount = discountRepo.findByCode(code);
		if(discount != null) {		
			DiscountDTO dto = DiscountMapper.getDTO(discount);
			dto.setOrderPrice(calculateOrderPrice(discount));
			dto.setPercent(calculateDiscountPercentage(discount.getPrice(), dto.getOrderPrice()));
			return Optional.of(dto);
		} else
			return Optional.empty();
	}	
	
	private BigDecimal calculateOrderPrice(Discount discount) {
		if(discount.getOrder() != null)
			return discount.getOrder().getItems().stream().map(item -> item.getPrice()).reduce(BigDecimal::add).get();
		else
			return BigDecimal.ZERO;
	}
	
	private Integer calculateDiscountPercentage(BigDecimal discountPrice, BigDecimal orderPrice) {
		if(BigDecimal.ZERO.compareTo(orderPrice) > 0)
			return discountPrice.multiply(new BigDecimal(100)).divide(orderPrice).intValue();
		else
			return 0;
	}	
	
	@Override
	public DiscountDTO save(@Valid DiscountDTO dto) {
		Discount discount = DiscountMapper.getEntity(dto);
		discount = discountRepo.save(discount);
		dto.setId(discount.getId());
		return dto;
	}

	@Override
	public DiscountDTO edit(@Valid DiscountDTO dto) {
		Discount discount = DiscountMapper.getEntity(dto);
		discount = discountRepo.save(discount);		
		return DiscountMapper.getDTO(discount);
	}

	@Override
	public void delete(@NotNull Long discountId) {		
		discountRepo.deleteById(discountId);
	}

}
