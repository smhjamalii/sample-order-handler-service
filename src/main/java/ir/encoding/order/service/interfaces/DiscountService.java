package ir.encoding.order.service.interfaces;

import java.util.Optional;

import ir.encoding.order.view.dto.discount.DiscountDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DiscountService {

	Optional<DiscountDTO> findById(Long discountId);
	Optional<DiscountDTO> findByCode(String code);
	DiscountDTO save(@Valid DiscountDTO dto);
	DiscountDTO edit(@Valid DiscountDTO dto);
	void delete(@NotNull Long discountId); 	
	
}
